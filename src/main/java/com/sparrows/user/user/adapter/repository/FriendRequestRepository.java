package com.sparrows.user.user.adapter.repository;

import com.sparrows.user.user.model.entity.FriendRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {
    Optional<FriendRequestEntity> findByRequesterIdAndRecipientId(Long requesterId, Long recipientId);
}