package com.ty.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dto.Admin;
import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.repository.AdminRepository;
import com.ty.eventmanagement.repository.ClubRepository;

@Repository
public class ClubDao {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ClubRepository clubRepository;

	public Club saveClub(int admin_id, Club club) {
		Optional<Admin> optional = adminRepository.findById(admin_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			club.setAdmin(optional.get());
			return clubRepository.save(club);
		}
	}

	public Club updateClub(int admin_id, int club_id, Club club) {
		Club findClub = getClubById(club_id);
		Optional<Admin> optional2 = adminRepository.findById(admin_id);
		if (club.getAddress() != null) {
			findClub.setAddress(club.getAddress());
			club.setAdmin(optional2.get());
			return clubRepository.save(findClub);
		}
		if (club.getWebsite() != null) {
			findClub.setWebsite(club.getWebsite());
			club.setAdmin(optional2.get());
			return clubRepository.save(findClub);
		}
		return null;
	}

	public Club getClubById(int id) {
		Optional<Club> optional = clubRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public List<Club> getAllClubs() {
		return clubRepository.findAll();
	}

	public String deleteClub(int club_id) {
		Optional<Club> optional = clubRepository.findById(club_id);
		if (optional.isEmpty()) {
			return "Event not found or deleted";
		} else {
			
			clubRepository.delete(optional.get());
			return "Event deleted";
		}
	}
}
