package com.ty.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.Login;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.dto.User;
import com.ty.eventmanagement.service.ClubBookingService;
import com.ty.eventmanagement.service.ClubService;
import com.ty.eventmanagement.service.SportsService;
import com.ty.eventmanagement.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ClubBookingService clubBookingService;

	@Autowired
	ClubService clubService;

	@Autowired
	SportsService service;

	@ApiOperation("To save the user")
	@ApiResponses({ @ApiResponse(code = 201, message = "user saved successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @ApiParam("User details") User user) {
		return userService.saveUser(user);
	}

	@ApiOperation("To fetch the user by id")
	@GetMapping("/users/{user_id}")
	public ResponseEntity<ResponseStructure<User>> getById(@PathVariable @ApiParam("User id") int user_id) {
		return userService.getById(user_id);
	}

	@ApiOperation("To update the user")
	@PutMapping("/users/{user_id}")
	@ApiResponses({ @ApiResponse(code = 201, message = "user updated successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable @ApiParam("User id") int user_id,
			@RequestBody @ApiParam("User details") User user) {
		return userService.updateUser(user_id, user);
	}

	@ApiOperation("To get the list of all the available clubs ")
	@ApiResponses({ @ApiResponse(code = 200, message = "user saved successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/userclubs")
	public ResponseEntity<ResponseStructure<List<Club>>> getAllClubs() {
		return clubService.getAllClubs();
	}

	@GetMapping("/userclubsports/{club_id}")
	public ResponseEntity<ResponseStructure<List<Sports>>> getAllSportsByClub(@PathVariable int club_id) {
		return service.getAllSportsByClub(club_id);
	}

	@ApiOperation("To book the sport by user ")
	@ApiResponses({ @ApiResponse(code = 201, message = "Sport booked successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/userbookings/{user_id}/{club_id}/{sport_id}")
	public ResponseEntity<ResponseStructure<ClubBooking>> saveBooking(@PathVariable @ApiParam("club id") int club_id,
			@PathVariable @ApiParam("Sports id") int sport_id, @PathVariable @ApiParam("User id") int user_id,
			@RequestBody @ApiParam("ClubBooking details") ClubBooking clubBooking) {
		return clubBookingService.saveBooking(club_id, sport_id, user_id, clubBooking);
	}

	@ApiOperation("Admin login")
	@ApiResponses({ @ApiResponse(code = 200, message = "User logged in successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<User>> validateUser(
			@RequestBody @ApiParam("Admin login credentials") Login login) {
		return userService.validateUser(login.getEmail(), login.getPassword());
	}
}
