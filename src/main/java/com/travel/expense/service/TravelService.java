package com.travel.expense.service;

import java.util.List;

import com.travel.expense.dto.TravelRequestDto;
import com.travel.expense.dto.TravelResponseDto;

public interface TravelService {

    String createRequest(TravelRequestDto dto, String username);

    List<TravelResponseDto> getMyRequests(String username);

    List<TravelResponseDto> getAllRequests();

    String approveRequest(Long requestId);

    String rejectRequest(Long requestId);
}
