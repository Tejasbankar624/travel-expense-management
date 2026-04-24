package com.travel.expense.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.expense.dto.TravelRequestDto;
import com.travel.expense.dto.TravelResponseDto;
import com.travel.expense.entity.RequestStatus;
import com.travel.expense.entity.TravelRequest;
import com.travel.expense.entity.User;
import com.travel.expense.exception.ResourceNotFoundException;
import com.travel.expense.repository.TravelRequestRepository;
import com.travel.expense.repository.UserRepository;
import com.travel.expense.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ CREATE REQUEST
    @Override
    public String createRequest(TravelRequestDto dto, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TravelRequest request = new TravelRequest();
        request.setDestination(dto.getDestination());
        request.setTravelDate(dto.getTravelDate());
        request.setReturnDate(dto.getReturnDate());
        request.setPurpose(dto.getPurpose());
        request.setStatus(RequestStatus.PENDING);
        request.setUser(user);

        travelRequestRepository.save(request);

        return "Travel request created successfully";
    }

    // ✅ GET MY REQUESTS
    @Override
    public List<TravelResponseDto> getMyRequests(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return travelRequestRepository.findByUser(user)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ GET ALL REQUESTS
    @Override
    public List<TravelResponseDto> getAllRequests() {

        return travelRequestRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ APPROVE REQUEST
    @Override
    public String approveRequest(Long id) {

        TravelRequest req = travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));

        req.setStatus(RequestStatus.APPROVED);
        travelRequestRepository.save(req);

        return "Request approved";
    }

    // ❌ REJECT REQUEST
    @Override
    public String rejectRequest(Long id) {

        TravelRequest req = travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));

        req.setStatus(RequestStatus.REJECTED);
        travelRequestRepository.save(req);

        return "Request rejected";
    }

    // 🔥 DTO MAPPER
    private TravelResponseDto mapToDto(TravelRequest request) {

        TravelResponseDto dto = new TravelResponseDto();

        dto.setId(request.getId());
        dto.setDestination(request.getDestination());
        dto.setTravelDate(request.getTravelDate());
        dto.setReturnDate(request.getReturnDate());
        dto.setPurpose(request.getPurpose());
        dto.setStatus(request.getStatus().name());
        dto.setRequestedBy(request.getUser().getUsername());

        return dto;
    }
}
