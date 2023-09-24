package com.ty.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.User;
import com.ty.eventmanagement.repository.ClubRepository;
import com.ty.eventmanagement.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ClubRepository clubRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getById(int user_id) {
		Optional<User> optional = userRepository.findById(user_id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public User updateUser(int user_id, User user) {
		User findUser = getById(user_id);
		if (user.getName() != null) {
			findUser.setName(user.getName());
		}
		if (user.getEmail() != null) {
			findUser.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			findUser.setPassword(user.getPassword());
		}
		if (user.getGender() != null) {
			findUser.setGender(user.getGender());
		}
		if (user.getAge() != 0) {
			findUser.setAge(user.getAge());
		}
		if (user.getPhone() != 0) {
			findUser.setPhone(user.getPhone());
		}
		return saveUser(findUser);

	}

	public List<Club> getAllClubs() {
		return clubRepository.findAll();
	}

	public User validateUser(String email, String password) {
		return userRepository.validateUser(email, password);
	}

}
