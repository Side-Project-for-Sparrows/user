package com.sparrows.user.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendResponseDto {
    private Long userId;
    private String nickname;
}