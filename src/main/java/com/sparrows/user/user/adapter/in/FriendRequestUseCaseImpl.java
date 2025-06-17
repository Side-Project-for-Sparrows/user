package com.sparrows.user.user.adapter.in;

import com.sparrows.user.user.exception.handling.FriendRequestAlreadyExistException;
import com.sparrows.user.user.exception.handling.FriendRequestNotFoundException;
import com.sparrows.user.user.model.dto.UserRelationRequestDto;
import com.sparrows.user.user.model.entity.FriendRequestEntity;
import com.sparrows.user.user.port.in.FriendRequestUseCase;
import com.sparrows.user.user.port.in.UserRelationUseCase;
import com.sparrows.user.user.port.out.FriendRequestPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendRequestUseCaseImpl implements FriendRequestUseCase {
    private final FriendRequestPort friendRequestPort;
    private final UserRelationUseCase userRelationUseCase;

    @Override
    public void requestFriend(UserRelationRequestDto request) {
        if (existsFriendRequest(request)) {
            throw new FriendRequestAlreadyExistException();
        }
        saveNewRequest(request);
        sendNotification(request);
    }

    @Override
    public void acceptFriend(UserRelationRequestDto request) {
        FriendRequestEntity requestEntity = getFriendRequestOrThrow(request);
        userRelationUseCase.createUserRelation(request);
        friendRequestPort.delete(requestEntity);
    }

    @Override
    public void rejectFriend(UserRelationRequestDto request) {
        friendRequestPort.delete(getFriendRequestOrThrow(request));
    }

    @Override
    public void removeFriend(UserRelationRequestDto request) {
        userRelationUseCase.deleteUserRelation(request);
    }

    private boolean existsFriendRequest(UserRelationRequestDto request) {
        return friendRequestPort.findByRequesterAndRecipient(
                request.getRequester(), request.getRecipient()).isPresent();
    }

    private void saveNewRequest(UserRelationRequestDto request) {
        FriendRequestEntity newRequest = FriendRequestEntity.builder()
                .requesterId(request.getRequester())
                .recipientId(request.getRecipient())
                .requestedAt(LocalDateTime.now())
                .build();
        friendRequestPort.save(newRequest);
    }

    private FriendRequestEntity getFriendRequestOrThrow(UserRelationRequestDto request) {
        return friendRequestPort.findByRequesterAndRecipient(
                request.getRequester(), request.getRecipient()
        ).orElseThrow(FriendRequestNotFoundException::new);
    }

    private void sendNotification(UserRelationRequestDto request) {
        // TODO: 알림 전송 로직
    }
}
