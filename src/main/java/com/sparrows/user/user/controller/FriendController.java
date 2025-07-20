package com.sparrows.user.user.controller;

import com.sparrows.user.user.model.dto.FriendNameRequestDto;
import com.sparrows.user.user.model.dto.FriendResponseDto;
import com.sparrows.user.user.model.dto.UserRelationRequestDto;
import com.sparrows.user.user.port.in.FriendRequestUseCase;
import com.sparrows.user.user.port.in.UserRelationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendRequestUseCase friendRequestUseCase;
    private final UserRelationUseCase userRelationUseCase;

    @GetMapping("/list")
    public ResponseEntity<List<FriendResponseDto>> getFriends(@RequestHeader("X-Requester-Id") Long requesterId) {
        return ResponseEntity.ok(userRelationUseCase.getFriends(requesterId));
    }

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
