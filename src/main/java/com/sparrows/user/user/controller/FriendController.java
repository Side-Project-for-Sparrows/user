package com.sparrows.user.user.controller;

import com.sparrows.user.user.model.dto.FriendNameRequestDto;
import com.sparrows.user.user.model.dto.UserRelationRequestDto;
import com.sparrows.user.user.port.in.FriendRequestUseCase;
import com.sparrows.user.user.port.in.UserRelationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendRequestUseCase friendRequestUseCase;
    private final UserRelationUseCase userRelationUseCase;

    @PostMapping("/request")
    public ResponseEntity<Void> request(@RequestBody UserRelationRequestDto request) {
        friendRequestUseCase.requestFriend(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> accept(@RequestBody UserRelationRequestDto request) {
        friendRequestUseCase.acceptFriend(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/reject")
    public ResponseEntity<Void> reject(@ModelAttribute UserRelationRequestDto request) {
        friendRequestUseCase.rejectFriend(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> remove(@ModelAttribute UserRelationRequestDto request) {
        friendRequestUseCase.removeFriend(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@RequestBody FriendNameRequestDto request) {
        userRelationUseCase.updateNickname(request.getUserId(), request.getFriendId(), request.getNewNickname());
        return ResponseEntity.ok().build();
    }
}
