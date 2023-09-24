package com.ty.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.eventmanagement.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("Select a from Admin a where a.email=?1 and a.password=?2")
	Admin validateAdmin(String email, String password);
}
