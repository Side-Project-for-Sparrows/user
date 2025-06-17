package com.sparrows.user.user.adapter.repository;

import com.sparrows.user.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginId(String loginId);

    boolean existsByNickname(String nickname);
}
