package com.sparrows.user.user.model.dto;

import com.sparrows.user.user.model.entity.UserEntity;
import com.sparrows.user.user.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private Long id;
    private String loginId;
    private String nickname;
    private UserType userType;
    private int point;
    private String accessToken;
    private String refreshToken;

    public static UserInfoDto fromEntity(UserEntity user, String accessToken, String refreshToken) {
        return new UserInfoDto(
                user.getId(),
                user.getLoginId(),
                user.getNickname(),
                user.getUserType(),
                user.getPoint(),
                accessToken,
                refreshToken
        );
    }
}