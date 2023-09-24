package com.ty.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.eventmanagement.dto.Sports;

public interface SportsRepository extends JpaRepository<Sports, Integer> {
	
	@Query("Select s from Sports s where s.club.id=?1")
	List<Sports> getSportsByClubId(int club_id);
}
