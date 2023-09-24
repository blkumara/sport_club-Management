package com.ty.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eventmanagement.dao.AdminDao;
import com.ty.eventmanagement.dao.ClubBookingDao;
import com.ty.eventmanagement.dao.ClubDao;
import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.exception.IdNotFoundException;

@Service
public class ClubService {

	@Autowired
	ClubDao clubDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	ClubBookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Club>> saveClub(int admin_id, Club club) {
		ResponseStructure<Club> responseStructure = new ResponseStructure<Club>();
		if (adminDao.getAdminById(admin_id) != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("created");
			responseStructure.setData(clubDao.saveClub(admin_id, club));
		} else {
			throw new IdNotFoundException("Admin id " + admin_id + " not found");
		}
		return new ResponseEntity<ResponseStructure<Club>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Club>> getClubById(int id) {
		ResponseStructure<Club> responseStructure = new ResponseStructure<Club>();
		Club club = clubDao.getClubById(id);
		if (club != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(club);
		} else {
			throw new IdNotFoundException("Club id " + id + " not found");
		}
		return new ResponseEntity<ResponseStructure<Club>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Club>> updateClub(int admin_id, int club_id, Club club) {
		ResponseStructure<Club> responseStructure = new ResponseStructure<Club>();
		if (clubDao.getClubById(club_id) != null && adminDao.getAdminById(admin_id) != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("success");
			responseStructure.setData(clubDao.updateClub(admin_id, club_id, club));
		} else {
			throw new IdNotFoundException("Admin or club not found");
		}
		return new ResponseEntity<ResponseStructure<Club>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Club>>> getAllClubs() {
		ResponseStructure<List<Club>> responseStructure = new ResponseStructure<List<Club>>();
		ResponseEntity<ResponseStructure<List<Club>>> entity = null;
		List<Club> clubs = clubDao.getAllClubs();
		if (clubs != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(clubs);
			entity = new ResponseEntity<ResponseStructure<List<Club>>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(clubs);
			entity = new ResponseEntity<ResponseStructure<List<Club>>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		return entity;
	}

	public ResponseEntity<ResponseStructure<String>> deleteClub(int club_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (clubDao.getClubById(club_id) != null) {
			List<ClubBooking> bookings = bookingDao.getAllBooking();
			for (ClubBooking booking : bookings) {
				if (club_id == booking.getClub().getId()) {
					booking.setClub(null);
					booking.setSports(null);
				}
			}
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(clubDao.deleteClub(club_id));
		} else {
			throw new IdNotFoundException("Club id " + club_id + " not found");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}
