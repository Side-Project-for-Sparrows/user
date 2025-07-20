package com.sparrows.user.user.port.in;

import com.sparrows.user.user.model.dto.FriendResponseDto;
import com.sparrows.user.user.model.dto.UserRelationRequestDto;

import java.util.List;

public interface UserRelationUseCase {
    void createUserRelation(UserRelationRequestDto request);
    void deleteUserRelation(UserRelationRequestDto request);
    void updateNickname(Long userId, Long friendId, String newNickname);
    List<FriendResponseDto> getFriends(Long userId);
}
