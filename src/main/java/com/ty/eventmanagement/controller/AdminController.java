package com.ty.eventmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eventmanagement.dto.Admin;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.Login;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.service.AdminService;
import com.ty.eventmanagement.service.ClubBookingService;
import com.ty.eventmanagement.service.ClubService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	ClubService clubService;

	@Autowired
	ClubBookingService bookingService;

	@ApiOperation("To save the admin")
	@ApiResponses({ @ApiResponse(code = 201, message = "admin created successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(
			@RequestBody @ApiParam("Admin details") @Valid Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@ApiOperation("To update Admin")
	@ApiResponses({ @ApiResponse(code = 201, message = "admin updated successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/admins/{id}")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@PathVariable @ApiParam("Admin id") int id,
			@RequestBody @ApiParam("Admin details") Admin admin) {
		return adminService.updateAdmin(id, admin);
	}

	@ApiOperation("To Fetch Admin by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "admin fetched successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/admins/{id}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@PathVariable @ApiParam("Admin id") int id) {
		return adminService.getAdminById(id);
	}

	@ApiOperation("To Delete Club")
	@ApiResponses({ @ApiResponse(code = 200, message = "club deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/adminclubs/{club_id}")
	public ResponseEntity<ResponseStructure<String>> deleteClub(@PathVariable @ApiParam("Club id") int club_id) {
		return clubService.deleteClub(club_id);
	}

	@ApiOperation("To get all bookings")
	@ApiResponses({ @ApiResponse(code = 201, message = "bookings fetched successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/adminbookings")
	public ResponseEntity<ResponseStructure<List<ClubBooking>>> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@ApiOperation("To login admin")
	@PostMapping("/admins/login")
	@ApiResponses({ @ApiResponse(code = 200, message = "Admin loggedin successfuly"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<ResponseStructure<Admin>> validateAdmin(
			@RequestBody @ApiParam("Admin Credentials") Login login) {
		return adminService.validateAdmin(login.getEmail(), login.getPassword());
	}
}
