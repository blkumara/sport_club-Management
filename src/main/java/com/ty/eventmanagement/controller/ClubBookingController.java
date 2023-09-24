//package com.ty.eventmanagement.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ty.eventmanagement.dto.ClubBooking;
//import com.ty.eventmanagement.dto.ResponseStructure;
//import com.ty.eventmanagement.service.ClubBookingService;
//
//@RestController
//public class ClubBookingController {
//
//	@Autowired
//	ClubBookingService clubBookingService;
//
//	@PostMapping("/bookings")
//	public ResponseStructure<ClubBooking> saveBooking(@PathVariable int user_id, @RequestBody ClubBooking clubBooking) {
//		return clubBookingService.saveBooking(user_id, clubBooking);
//	}
//}
