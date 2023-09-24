package com.ty.eventmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ClubBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String clubAddress;
	private String sportName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime entryTime;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime exitTime;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Club club;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private User user;
	@JsonIgnore
	@ManyToOne
	private Sports sports;

}
