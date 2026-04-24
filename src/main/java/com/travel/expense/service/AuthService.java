package com.travel.expense.service;

import com.travel.expense.dto.AuthResponseDto;
import com.travel.expense.dto.LoginRequestDto;
import com.travel.expense.dto.RegisterRequestDto;

public interface AuthService {

    String registerUser(RegisterRequestDto registerRequestDto);

    AuthResponseDto loginUser(LoginRequestDto loginRequestDto);
}
