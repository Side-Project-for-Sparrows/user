package com.sparrows.user.user.adapter.out;

import com.sparrows.user.user.adapter.repository.UserRepository;
import com.sparrows.user.user.model.entity.UserEntity;
import com.sparrows.user.user.port.out.UserDataBasePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDatabaseAdapter implements UserDataBasePort {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }
}
