package com.ty.eventmanagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dto.Admin;
import com.ty.eventmanagement.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	AdminRepository adminRepository;

	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Admin updateAdmin(int id, Admin admin) {
		Admin findAdmin = getAdminById(id);
		if (admin.getName() != null) {
			findAdmin.setName(admin.getName());
		}
		if (admin.getEmail() != null) {
			findAdmin.setEmail(admin.getEmail());
		}
		if (admin.getPassword() != null) {
			findAdmin.setPassword(admin.getPassword());
		}
		if (admin.getPhone() != 0) {
			findAdmin.setPhone(admin.getPhone());
		}
		return saveAdmin(findAdmin);
	}

	public Admin getAdminById(int id) {
		Optional<Admin> optional = adminRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Admin validateAdmin(String email, String password) {
		return adminRepository.validateAdmin(email, password);
	}

}
