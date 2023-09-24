package com.ty.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.service.ClubService;
import com.ty.eventmanagement.service.SportsService;

@RestController
public class SportsController {

	@Autowired
	SportsService service;

	@Autowired
	ClubService clubService;

	@PostMapping("/sports/{club_id}")
	public ResponseEntity<ResponseStructure<Sports>> saveSports(@PathVariable int club_id, @RequestBody Sports sports) {
		return service.saveSports(club_id, sports);
	}

	@GetMapping("/sports/{id}")
	public ResponseEntity<ResponseStructure<Sports>> getSportById(@PathVariable int id) {
		return service.getSportById(id);
	}


	@PutMapping("/sports/{club_id}/{sports_id}")
	public ResponseEntity<ResponseStructure<Sports>> updateSports(@PathVariable int club_id,
			@PathVariable int sports_id, @RequestBody Sports sports) {
		return service.updateSports(club_id, sports_id, sports);
	}

	@DeleteMapping("/sports/{club_id}/{sports_id}")
	public ResponseEntity<ResponseStructure<String>> deleteSports(@PathVariable int club_id,
			@PathVariable int sports_id) {
		return service.deleteSports(club_id, sports_id);
	}

}
