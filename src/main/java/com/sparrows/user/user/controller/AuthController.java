package com.sparrows.user.user.controller;

import com.sparrows.user.user.model.dto.LoginRequestDto;
import com.sparrows.user.user.model.dto.LoginResponseDto;
import com.sparrows.user.user.model.dto.RegisterRequestDto;
import com.sparrows.user.user.model.dto.RegisterResponseDto;
import com.sparrows.user.user.port.in.UserAuthUseCase;
import com.sparrows.user.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/user/auth")
@RestController
public class AuthController {
    @Autowired
    UserAuthUseCase userAuthUseCase;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = userAuthUseCase.loginIfValidUser(loginRequestDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<RegisterResponseDto> joinUser(@RequestBody RegisterRequestDto registerRequestDto){
        RegisterResponseDto response = userAuthUseCase.registerIfLocationMatches(registerRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refreshAccessToken(@RequestHeader("refreshToken") String refreshToken) {
        Long userId = jwtTokenUtil.verifyRefreshToken(refreshToken);
        String accessToken = jwtTokenUtil.generateAccessToken(userId);

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
