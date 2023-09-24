package com.ty.eventmanagement.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String website;
	private String address;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Admin admin;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "club")
	private List<Sports> sports;
}
