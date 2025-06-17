package com.sparrows.user.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendNameRequestDto {
    private Long userId;
    private Long friendId;
    private String newNickname;
}
