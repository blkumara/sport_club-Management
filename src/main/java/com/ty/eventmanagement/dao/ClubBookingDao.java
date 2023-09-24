package com.ty.eventmanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.ClubBooking;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.dto.User;
import com.ty.eventmanagement.repository.ClubBookingRepository;

@Repository
public class ClubBookingDao {

	@Autowired
	ClubBookingRepository clubBookingRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	ClubDao clubDao;

	@Autowired
	SportsDao sportsDao;

	public ClubBooking saveBooking(int club_id, int sport_id, int user_id, ClubBooking booking) {
		User user = userDao.getById(user_id);
		Club club = clubDao.getClubById(club_id);
		Sports sports = sportsDao.getSportById(sport_id);
		if (user != null) {
			String sportName = sports.getSportsName();
			String clubName = club.getAddress();
			booking.setClubAddress(clubName);
			booking.setSportName(sportName);
			booking.setClub(club);
			booking.setUser(user);
			booking.setSports(sports);
			return clubBookingRepository.save(booking);
		} else {
			return null;
		}

	}

	public List<ClubBooking> getAllBooking() {
		return clubBookingRepository.findAll();
	}

	
}
