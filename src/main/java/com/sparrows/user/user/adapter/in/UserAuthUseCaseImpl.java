package com.sparrows.user.user.adapter.in;

import com.sparrows.user.common.HashFunction;
import com.sparrows.user.user.exception.handling.*;
import com.sparrows.user.user.model.dto.*;
import com.sparrows.user.user.model.entity.UserEntity;
import com.sparrows.user.user.model.enums.UserType;
import com.sparrows.user.user.port.in.UserAuthUseCase;
import com.sparrows.user.user.port.out.UserEventPort;
import com.sparrows.user.user.port.out.SchoolPort;
import com.sparrows.user.user.port.out.UserDataBasePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserAuthUseCaseImpl implements UserAuthUseCase {

    @Autowired
    private UserDataBasePort userDataBasePort;

    @Autowired
    private SchoolPort schoolPort;

    @Autowired
    private UserEventPort userEventPort;

    @Override
    @Transactional
    public RegisterResponseDto registerIfLocationMatches(RegisterRequestDto request) {
        validateRegisterRequest(request);

        UserEntity savedUser = saveUser(request);
        userEventPort.publishUserCreatedEvent(savedUser.getId(), request.getSchoolId(), request.getUserType());

        return new RegisterResponseDto(UserInfoDto.fromEntity(savedUser));
    }

    @Override
    public LoginResponseDto loginIfValidUser(LoginRequestDto request) {
        UserEntity user = userDataBasePort.findByLoginId(request.getId())
                .orElseThrow(UserNotFoundException::new);

        if (!HashFunction.hash(request.getPw()).equals(user.getLoginPassword())) {
            throw new InvalidPasswordException();
        }

        return new LoginResponseDto(UserInfoDto.fromEntity(user));
    }

    private void validateRegisterRequest(RegisterRequestDto request) {
        checkLoginIdAvailable(request.getId());
        validatePassword(request.getPw());
        validateAndCheckNickname(request.getNickname());

        if (request.getUserType() == UserType.OFFICIAL) {
            validateStudentLocation(request);
        } else {
            log.info("위치 검증 건너뜀 - userType: {}", request.getUserType());
        }
    }

    private UserEntity saveUser(RegisterRequestDto request) {
        UserEntity user = UserEntity.builder()
                .loginId(request.getId())
                .loginPassword(HashFunction.hash(request.getPw()))
                .nickname(request.getNickname())
                .userType(request.getUserType())
                .point(0)
                .isValid(true)
                .build();

        return userDataBasePort.save(user);
    }

    private void validateStudentLocation(RegisterRequestDto req) {
        SchoolValidateRequest request = new SchoolValidateRequest(req.getSchoolId(), req.getLatitude(), req.getLongitude());
        boolean matched = schoolPort.isSameLocationWithSchoolId(request);

        if (!matched) throw new LocationMismatchException();
    }

    private void validatePassword(String pw) {
        if (pw == null || pw.length() < 8 || !pw.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            throw new InvalidPasswordException();
        }
    }

    private void validateAndCheckNickname(String nickname) {
        if (nickname == null || nickname.length() < 2) {
            throw new InvalidNicknameException();
        }

        if (userDataBasePort.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistException();
        }
    }

    private void checkLoginIdAvailable(String loginId) {
        userDataBasePort.findByLoginId(loginId).ifPresent(user -> {
            throw new UserAlreadyExistException();
        });
    }
}
