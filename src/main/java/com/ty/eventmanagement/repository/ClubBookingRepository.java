package com.ty.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.eventmanagement.dto.ClubBooking;

public interface ClubBookingRepository extends JpaRepository<ClubBooking, Integer> {

	
}
