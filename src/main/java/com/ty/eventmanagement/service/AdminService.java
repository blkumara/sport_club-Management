package com.ty.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eventmanagement.dao.AdminDao;
import com.ty.eventmanagement.dto.Admin;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.exception.IdNotFoundException;
import com.ty.eventmanagement.exception.InvalidCredentials;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("created");
		responseStructure.setData(adminDao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(int id, Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin findAdmin = adminDao.getAdminById(id);
		if (findAdmin != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(adminDao.updateAdmin(id, admin));
		} else {
			throw new IdNotFoundException("Admin id " + id + " not found");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin findAdmin = adminDao.getAdminById(id);
		if (findAdmin != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(findAdmin);
		} else {
			throw new IdNotFoundException("Admin id " + id + " not found");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Admin>> validateAdmin(String email, String password) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin validateAdmin = adminDao.validateAdmin(email, password);
		if (validateAdmin != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(validateAdmin);
		} else {
			throw new InvalidCredentials("Admin email or password wrong");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
	}

}
