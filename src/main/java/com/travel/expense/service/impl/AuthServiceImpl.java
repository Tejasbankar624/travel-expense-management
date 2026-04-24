package com.travel.expense.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.expense.dto.AuthResponseDto;
import com.travel.expense.dto.LoginRequestDto;
import com.travel.expense.dto.RegisterRequestDto;
import com.travel.expense.entity.Role;
import com.travel.expense.entity.RoleName;
import com.travel.expense.entity.User;
import com.travel.expense.repository.RoleRepository;
import com.travel.expense.repository.UserRepository;
import com.travel.expense.security.JwtUtil;
import com.travel.expense.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String registerUser(RegisterRequestDto registerRequestDto) {

        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            return "Email already exists";
        }

        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            return "Username already exists";
        }

        RoleName roleName = RoleName.valueOf(registerRequestDto.getRole().toUpperCase());

        Role role = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName(roleName);
                    return roleRepository.save(newRole);
                });

        User user = new User();
        user.setFullName(registerRequestDto.getFullName());
        user.setEmail(registerRequestDto.getEmail());
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponseDto loginUser(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            String token = jwtUtil.generateToken(loginRequestDto.getUsername());

            return new AuthResponseDto(token, "Login successful");
        }

        return new AuthResponseDto(null, "Invalid username or password");
    }
}
