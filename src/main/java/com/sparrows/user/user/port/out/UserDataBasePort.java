package com.sparrows.user.user.port.out;

import com.sparrows.user.user.model.entity.UserEntity;
import java.util.Optional;

public interface UserDataBasePort{
    Optional<UserEntity> findByLoginId(String loginId); // 로그인 ID로 검색

    Optional<UserEntity> findById(Long id);

    boolean existsByNickname(String nickname);

    UserEntity save(UserEntity userEntity);
}
