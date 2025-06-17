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

    public static UserInfoDto fromEntity(UserEntity user) {
        return new UserInfoDto(
                user.getId(),
                user.getLoginId(),
                user.getNickname(),
                user.getUserType(),
                user.getPoint()
        );
    }
}