package com.ty.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dao.ClubBookingDao;
import com.ty.eventmanagement.dao.ClubDao;
import com.ty.eventmanagement.dao.SportsDao;
import com.ty.eventmanagement.dao.UserDao;
import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.ResponseStructure;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.dto.User;
import com.ty.eventmanagement.exception.IdNotFoundException;
import com.ty.eventmanagement.exception.NoDataAvailableException;
import com.ty.eventmanagement.exception.SlotBookedException;

@Repository
public class ClubBookingService {

	@Autowired
	ClubBookingDao bookingDao;

	@Autowired
	ClubDao clubDao;

	@Autowired
	UserDao userDao;

	@Autowired
	SportsDao sportsDao;

	public ResponseEntity<ResponseStructure<ClubBooking>> saveBooking(int club_id, int sport_id, int user_id,
			ClubBooking booking) {
		ResponseStructure<ClubBooking> responseStructure = new ResponseStructure<ClubBooking>();
		Club club = clubDao.getClubById(club_id);
		User user = userDao.getById(user_id);
		Sports sports = sportsDao.getSportById(sport_id);
		if (club != null && user != null && sports != null) {
			for (ClubBooking findBooking : bookingDao.getAllBooking()) {
				if (findBooking.getClub().getId() == club_id) {
					for (ClubBooking findSports : bookingDao.getAllBooking()) {
						if (findSports.getSports().getId() == sport_id) {
							if (findBooking.getDate().toString().equals(booking.getDate().toString())) {
								if ((booking.getEntryTime().isAfter(findBooking.getEntryTime()))
										&& (booking.getExitTime().isAfter(findBooking.getExitTime()))) {
									responseStructure.setStatusCode(HttpStatus.CREATED.value());
									responseStructure.setMessage("created");
									responseStructure
											.setData(bookingDao.saveBooking(club_id, sport_id, user_id, booking));
								} else if ((booking.getEntryTime().isBefore(findBooking.getEntryTime()))
										&& (booking.getExitTime().isBefore(findBooking.getEntryTime()))) {
									responseStructure.setStatusCode(HttpStatus.CREATED.value());
									responseStructure.setMessage("created");
									responseStructure
											.setData(bookingDao.saveBooking(club_id, sport_id, user_id, booking));
								} else {
									throw new SlotBookedException("Slot already booked.Please Check");
								}
							}
						}
					}
				}
			}
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("created");
			responseStructure.setData(bookingDao.saveBooking(club_id, sport_id, user_id, booking));
		} else {
			throw new IdNotFoundException("Club id or user id not found.Please check");
		}
		return new ResponseEntity<ResponseStructure<ClubBooking>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<ClubBooking>>> getAllBookings() {
		ResponseStructure<List<ClubBooking>> responseStructure = new ResponseStructure<List<ClubBooking>>();
		List<ClubBooking> bookings = bookingDao.getAllBooking();
		ResponseEntity<ResponseStructure<List<ClubBooking>>> entity = null;
		if (bookings.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookings);

			return new ResponseEntity<ResponseStructure<List<ClubBooking>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoDataAvailableException("No bookings available");
		}
	}

}

//if (findBooking.getDate().toString().equals(booking.getDate().toString())) {
//	if ((booking.getEntryTime().isAfter(findBooking.getEntryTime()))
//			&& (booking.getExitTime().isAfter(findBooking.getExitTime()))) {
//		responseStructure.setStatusCode(HttpStatus.CREATED.value());
//		responseStructure.setMessage("created");
//		responseStructure.setData(bookingDao.saveBooking(club_id, sport_id, user_id, booking));
//	} else if ((booking.getEntryTime().isBefore(findBooking.getEntryTime()))
//			&& (booking.getExitTime().isBefore(findBooking.getEntryTime()))) {
//		responseStructure.setStatusCode(HttpStatus.CREATED.value());
//		responseStructure.setMessage("created");
//		responseStructure.setData(bookingDao.saveBooking(club_id, sport_id, user_id, booking));
//	} else {
//		throw new SlotBookedException("Slot already booked.Please Check");
//	}
//}
