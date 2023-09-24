package com.ty.eventmanagement.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name should not be blank")
	private String name;
	private long phone;
	@NotBlank(message = "Email should not be blank")
	private String email;
	@NotBlank(message = "Password should not be blank")
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<Club> club;

}
