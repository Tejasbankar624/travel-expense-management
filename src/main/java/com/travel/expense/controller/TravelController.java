package com.travel.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.travel.expense.dto.TravelRequestDto;
import com.travel.expense.dto.TravelResponseDto;
import com.travel.expense.exception.ResourceNotFoundException;
import com.travel.expense.service.TravelService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    // 👨‍💻 EMPLOYEE → CREATE REQUEST
    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String createRequest(@RequestBody TravelRequestDto dto,
                                Authentication authentication) {

        String username = authentication.getName();
        return travelService.createRequest(dto, username);
    }
    @GetMapping("/test-error")
    public String testError() {
        throw new ResourceNotFoundException("Test exception working");
    }


    // 👨‍💻 EMPLOYEE → VIEW OWN REQUESTS
    @GetMapping("/my-requests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<TravelResponseDto> getMyRequests(Authentication authentication) {

        String username = authentication.getName();
        return travelService.getMyRequests(username);
    }

    // 👨‍💼 MANAGER → VIEW ALL REQUESTS
    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public List<TravelResponseDto> getAllRequests() {
        return travelService.getAllRequests();
    }

    // 👨‍💼 MANAGER → APPROVE REQUEST
    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public String approveRequest(@PathVariable Long id) {
        return travelService.approveRequest(id);
    }

    // 👨‍💼 MANAGER → REJECT REQUEST
    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public String rejectRequest(@PathVariable Long id) {
        return travelService.rejectRequest(id);
    }
}


