package com.sparrows.user.user.adapter.out;

import com.sparrows.user.user.adapter.repository.UserRelationRepository;
import com.sparrows.user.user.adapter.repository.UserRepository;
import com.sparrows.user.user.exception.handling.UserNotFoundException;
import com.sparrows.user.user.model.entity.UserRelationEntity;
import com.sparrows.user.user.port.out.UserRelationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserRelationAdapter implements UserRelationPort {

    private final UserRelationRepository userRelationRepository;
    private final UserRepository userRepository;

    @Override
    public boolean existsByUsers(Long user1, Long user2) {
        return userRelationRepository.existsByUser1AndUser2(user1, user2);
    }

    @Override
    @Transactional
    public void deleteByUsers(Long user1, Long user2) {
        userRelationRepository.deleteByUser1AndUser2(user1, user2);
    }

    @Override
    public UserRelationEntity save(UserRelationEntity entity) {
        return userRelationRepository.save(entity);
    }

    @Override
    public void updateNickname(Long userId, Long friendId, String newNickname) {
        userRelationRepository.updateNickname(userId, friendId, newNickname);
    }

    @Override
    public String getNicknameByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new)
                .getNickname();
    }
}
