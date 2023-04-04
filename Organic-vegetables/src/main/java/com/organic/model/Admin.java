package com.organic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	
	@NotNull(message = "Admin name should not be null")
	@NotBlank(message = "Admin Name should not black")
	private String adminName;
	
	private String contactNumber;
	@Email
	private String emailId;
	
	@Size(min = 3,max = 10,message = "Password should be contain min 3 and max 10 ")
	private String password;
	
	
}
