package com.sparrows.user.user.port.in;

import com.sparrows.user.user.model.dto.UserRelationRequestDto;

public interface UserRelationUseCase {
    void createUserRelation(UserRelationRequestDto request);
    void deleteUserRelation(UserRelationRequestDto request);
    void updateNickname(Long userId, Long friendId, String newNickname);
}
