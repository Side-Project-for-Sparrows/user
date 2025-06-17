package com.sparrows.user.user.adapter.out;

import com.sparrows.user.user.adapter.repository.FriendRequestRepository;
import com.sparrows.user.user.model.entity.FriendRequestEntity;
import com.sparrows.user.user.port.out.FriendRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FriendRequestAdapter implements FriendRequestPort {

    private final FriendRequestRepository repository;

    @Override
    public Optional<FriendRequestEntity> findByRequesterAndRecipient(Long requesterId, Long recipientId) {
        return repository.findByRequesterIdAndRecipientId(requesterId, recipientId);
    }

    @Override
    public FriendRequestEntity save(FriendRequestEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(FriendRequestEntity entity) {
        repository.delete(entity);
    }
}
