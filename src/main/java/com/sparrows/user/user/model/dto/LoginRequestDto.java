package com.sparrows.user.user.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    String id;
    String pw;
}
