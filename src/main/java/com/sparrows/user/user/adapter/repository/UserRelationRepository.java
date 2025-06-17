package com.sparrows.user.user.adapter.repository;

import com.sparrows.user.user.model.entity.UserRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRelationRepository extends JpaRepository<UserRelationEntity, Long> {
    boolean existsByUser1AndUser2(Long user1, Long user2);
    void deleteByUser1AndUser2(Long user1, Long user2);

    @Modifying
    @Transactional
    @Query("update UserRelationEntity ur set ur.nickname = :nickname where ur.user1 = :userId and ur.user2 = :friendId")
    void updateNickname(Long userId, Long friendId, String nickname);
}
