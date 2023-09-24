package com.ty.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eventmanagement.dto.Club;
import com.ty.eventmanagement.dto.Sports;
import com.ty.eventmanagement.repository.SportsRepository;

@Repository
public class SportsDao {

	@Autowired
	SportsRepository repository;

	@Autowired
	ClubDao clubDao;

	public Sports saveSports(int club_id, Sports sports) {
		Club club = clubDao.getClubById(club_id);
		if (club == null) {
			return null;
		} else {
			sports.setClub(club);
			return repository.save(sports);
		}
	}

	public Sports getSportById(int id) {
		Optional<Sports> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public List<Sports> getAllSports() {
		return repository.findAll();
	}

	public Sports updateSports(int club_id, int sports_id, Sports sports) {
		Sports findSports = getSportById(sports_id);
		Club club = clubDao.getClubById(club_id);
		if (sports.getSportsName() != null && sports.getClub().getId() == club_id) {
			sports.setClub(club);
			findSports.setSportsName(sports.getSportsName());
		}
		if (sports.getType() != null) {
			sports.setClub(club);
			findSports.setType(sports.getType());
		}
		return repository.save(sports);
	}

	public String deleteSports(int club_id, int sports_id) {
		Optional<Sports> optional = repository.findById(sports_id);
		if (optional.isPresent() && optional.get().getClub().getId() == club_id) {
			repository.delete(optional.get());
			return "Sports deleted";
		} else {
			return "Sports not found";
		}
	}

	public List<Sports> getAllSportsByClub(int club_id) {
		Club club = clubDao.getClubById(club_id);
		if (club != null) {
			List<Sports> sports = repository.getSportsByClubId(club_id);
			return sports;
		} else {
			return null;
		}
	}

}
