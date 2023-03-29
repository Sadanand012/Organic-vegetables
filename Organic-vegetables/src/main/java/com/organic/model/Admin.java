package com.organic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@NotNull(message = "Admin name should not be null")
	@NotBlank(message = "Admin Name should not black")
	private String adminName;
	
	@Pattern(regexp="(0|91)?[7-9][0-9]{9}")
	private String contactNumber;
	@Email
	private String emailId;
	
	private String password;
	
	@OneToOne
	private User user;
	
}
