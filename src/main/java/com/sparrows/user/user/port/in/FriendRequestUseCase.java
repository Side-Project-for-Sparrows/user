package com.sparrows.user.user.port.in;

import com.sparrows.user.user.model.dto.UserRelationRequestDto;

public interface FriendRequestUseCase {
    void requestFriend(UserRelationRequestDto request);
    void acceptFriend(UserRelationRequestDto request);
    void rejectFriend(UserRelationRequestDto request);
    void removeFriend(UserRelationRequestDto request);
}
