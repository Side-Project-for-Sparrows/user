package com.sparrows.user.user.port.out;

import com.sparrows.user.user.model.dto.FriendResponseDto;
import com.sparrows.user.user.model.entity.UserRelationEntity;

import java.util.List;

public interface UserRelationPort {
    boolean existsByUsers(Long user1, Long user2);
    void deleteByUsers(Long user1, Long user2);
    UserRelationEntity save(UserRelationEntity entity);
    void updateNickname(Long userId, Long friendId, String newNickname);
    String getNicknameByUserId(Long userId);
    List<FriendResponseDto> findFriendsByUserId(Long userId);
}

