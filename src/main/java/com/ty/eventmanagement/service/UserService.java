package com.ty.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eventmanagement.dao.UserDao;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.dto.User;
import com.ty.eventmanagement.exception.IdNotFoundException;
import com.ty.eventmanagement.exception.InvalidCredentials;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("created");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> getById(int user_id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User findUser = userDao.getById(user_id);
		if (findUser != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("success");
			responseStructure.setData(findUser);
		} else {
			throw new IdNotFoundException("User id " + user_id + " not found");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int user_id, User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User findUser = userDao.getById(user_id);
		if (findUser != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(userDao.updateUser(user_id, user));
		} else {
			throw new IdNotFoundException("User id " + user_id + " not found");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> validateUser(String email, String password) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User validateUser = userDao.validateUser(email, password);
		if (validateUser != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(validateUser);
		} else {
			throw new InvalidCredentials("User email or password wrong");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}
}
