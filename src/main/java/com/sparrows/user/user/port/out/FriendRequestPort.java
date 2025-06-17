package com.sparrows.user.user.port.out;

import com.sparrows.user.user.model.entity.FriendRequestEntity;

import java.util.Optional;

public interface FriendRequestPort {
    Optional<FriendRequestEntity> findByRequesterAndRecipient(Long requesterId, Long recipientId);
    FriendRequestEntity save(FriendRequestEntity entity);
    void delete(FriendRequestEntity entity);
}
