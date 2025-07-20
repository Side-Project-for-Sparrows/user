package com.sparrows.user.user.adapter.in;

import com.sparrows.user.user.exception.handling.UserRelationAlreadyExistException;
import com.sparrows.user.user.exception.handling.UserRelationNotFoundException;
import com.sparrows.user.user.model.dto.FriendResponseDto;
import com.sparrows.user.user.model.dto.UserRelationRequestDto;
import com.sparrows.user.user.model.entity.UserRelationEntity;
import com.sparrows.user.user.port.in.UserRelationUseCase;
import com.sparrows.user.user.port.out.UserRelationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserRelationUseCaseImpl implements UserRelationUseCase {

    private final UserRelationPort userRelationPort;

    @Override
    public List<FriendResponseDto> getFriends(Long userId) {
        return userRelationPort.findFriendsByUserId(userId);
    }

    @Override
    public void createUserRelation(UserRelationRequestDto request) {
        if (isRelationExists(request)) {
            throw new UserRelationAlreadyExistException();
        }

        String requesterNickname = userRelationPort.getNicknameByUserId(request.getRequester());
        String recipientNickname = userRelationPort.getNicknameByUserId(request.getRecipient());

        userRelationPort.save(UserRelationEntity.builder()
                .user1(request.getRequester())
                .user2(request.getRecipient())
                .nickname(recipientNickname)
                .build());

        userRelationPort.save(UserRelationEntity.builder()
                .user1(request.getRecipient())
                .user2(request.getRequester())
                .nickname(requesterNickname)
                .build());
    }

    @Override
    public void deleteUserRelation(UserRelationRequestDto request) {
        if (!isRelationExists(request)) {
            throw new UserRelationNotFoundException();
        }

        userRelationPort.deleteByUsers(request.getRequester(), request.getRecipient());
        userRelationPort.deleteByUsers(request.getRecipient(), request.getRequester());
    }

    @Override
    public void updateNickname(Long userId, Long friendId, String newNickname) {
        if (!userRelationPort.existsByUsers(userId, friendId)) {
            throw new UserRelationNotFoundException();
        }

        userRelationPort.updateNickname(userId, friendId, newNickname);
    }

    private boolean isRelationExists(UserRelationRequestDto request) {
        return userRelationPort.existsByUsers(request.getRequester(), request.getRecipient());
    }
}
