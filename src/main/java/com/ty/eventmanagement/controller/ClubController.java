package com.ty.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.service.ClubService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ClubController {

	@Autowired
	ClubService clubService;

	@ApiOperation("To save the club")
	@PostMapping("/clubs/{admin_id}")
	public ResponseEntity<ResponseStructure<Club>> saveClub(@PathVariable @ApiParam("Admin id") int admin_id,
			@RequestBody @ApiParam("Club details") Club club) {
		return clubService.saveClub(admin_id, club);
	}

	@ApiOperation("To fetch club by id")
	@GetMapping("/clubs/{id}")
	public ResponseEntity<ResponseStructure<Club>> getClubById(@PathVariable @ApiParam("Club id") int id) {
		return clubService.getClubById(id);
	}

	@ApiOperation("To Update club")
	@PutMapping("/clubs/{admin_id}/{club_id}")
	public ResponseEntity<ResponseStructure<Club>> updateClub(@PathVariable @ApiParam("Admin id") int admin_id,
			@PathVariable @ApiParam("Club id") int club_id, @RequestBody @ApiParam("Club details") Club club) {
		return clubService.updateClub(admin_id, club_id, club);
	}

}
