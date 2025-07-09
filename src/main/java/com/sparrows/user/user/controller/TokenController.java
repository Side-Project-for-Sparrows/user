package com.sparrows.user.user.controller;

import com.sparrows.user.user.exception.handling.RefreshTokenExpiredException;
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
@RequestMapping("/token")
@RestController
public class TokenController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/publicKey")
    public ResponseEntity<String> getPublicPem() {
        String result = jwtTokenUtil.getPublicKeyPem();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
