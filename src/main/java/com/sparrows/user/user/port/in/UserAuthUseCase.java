package com.sparrows.user.user.port.in;

import com.sparrows.user.user.model.dto.LoginRequestDto;
import com.sparrows.user.user.model.dto.LoginResponseDto;
import com.sparrows.user.user.model.dto.RegisterRequestDto;
import com.sparrows.user.user.model.dto.RegisterResponseDto;

public interface UserAuthUseCase {
    RegisterResponseDto registerIfLocationMatches(RegisterRequestDto request);
    LoginResponseDto loginIfValidUser(LoginRequestDto request);
}
