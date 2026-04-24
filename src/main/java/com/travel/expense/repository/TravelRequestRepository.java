package com.travel.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.expense.entity.TravelRequest;
import com.travel.expense.entity.User;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {

    List<TravelRequest> findByUser(User user);
}
