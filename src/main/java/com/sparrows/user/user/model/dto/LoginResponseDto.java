package com.sparrows.user.user.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private long id;
    private UserInfoDto userInfo;

    public LoginResponseDto(UserInfoDto userInfo) {
        this.id = userInfo.getId();
        this.userInfo = userInfo;
    }
}
