package com.ty.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eventmanagement.dao.ClubBookingDao;
import com.ty.eventmanagement.dao.ClubDao;
import com.ty.eventmanagement.dao.SportsDao;
import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.exception.IdNotFoundException;
import com.ty.eventmanagement.exception.NoDataAvailableException;

@Service
public class SportsService {

	@Autowired
	SportsDao sportsDao;

	@Autowired
	ClubDao clubDao;

	@Autowired
	ClubBookingDao clubBookingDao;

	public ResponseEntity<ResponseStructure<Sports>> saveSports(int club_id, Sports sports) {
		ResponseStructure<Sports> responseStructure = new ResponseStructure<Sports>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("created");
		responseStructure.setData(sportsDao.saveSports(club_id, sports));
		return new ResponseEntity<ResponseStructure<Sports>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Sports>> getSportById(int id) {
		ResponseStructure<Sports> responseStructure = new ResponseStructure<Sports>();
		Sports sports = sportsDao.getSportById(id);
		if (sports != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(sports);
			return new ResponseEntity<ResponseStructure<Sports>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Club id " + id + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Sports>>> getAllSports() {
		ResponseStructure<List<Sports>> responseStructure = new ResponseStructure<List<Sports>>();
		List<Sports> sports = sportsDao.getAllSports();
		if (sports.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(sports);
			return new ResponseEntity<ResponseStructure<List<Sports>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoDataAvailableException("No sports available");
		}
	}

	public ResponseEntity<ResponseStructure<Sports>> updateSports(int club_id, int sports_id, Sports sports) {
		ResponseStructure<Sports> responseStructure = new ResponseStructure<Sports>();
		Club findClub = clubDao.getClubById(club_id);
		Sports findSports = sportsDao.getSportById(sports_id);
		if (findClub != null && findSports != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(sportsDao.updateSports(club_id, sports_id, sports));
			return new ResponseEntity<ResponseStructure<Sports>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Club or Sports ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteSports(int club_id, int sports_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		Club findClub = clubDao.getClubById(club_id);
		Sports findSports = sportsDao.getSportById(sports_id);
		if (findClub != null && findSports != null) {
			List<ClubBooking> bookings = clubBookingDao.getAllBooking();
			for (ClubBooking booking : bookings) {
				if (sports_id == booking.getSports().getId()) {
					booking.setSports(null);
				}
			}
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(sportsDao.deleteSports(club_id, sports_id));
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Club or Sports ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Sports>>> getAllSportsByClub(int club_id) {
		ResponseStructure<List<Sports>> responseStructure = new ResponseStructure<List<Sports>>();
		List<Sports> sports = sportsDao.getAllSportsByClub(club_id);
		if (sports.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(sports);
			return new ResponseEntity<ResponseStructure<List<Sports>>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

}
