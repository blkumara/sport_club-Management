package com.ty.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.eventmanagement.dto.Club;

public interface ClubRepository extends JpaRepository<Club, Integer> {
	
}
