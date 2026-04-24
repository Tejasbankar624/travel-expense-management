package com.travel.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.expense.dto.AuthResponseDto;
import com.travel.expense.dto.LoginRequestDto;
import com.travel.expense.dto.RegisterRequestDto;
import com.travel.expense.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {

        String response = authService.registerUser(registerRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {

        AuthResponseDto response = authService.loginUser(loginRequestDto);
        return ResponseEntity.ok(response);
    }
}
