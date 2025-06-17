package com.sparrows.user.user.model.dto;

import com.sparrows.user.user.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String id;
    private String pw;
    private int schoolId;
    private String nickname;
    private UserType userType;
    private double latitude;
    private double longitude;
}
