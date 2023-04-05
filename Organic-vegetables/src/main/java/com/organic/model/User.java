package com.organic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Column(unique = true)
	private String userId;

	@Size(min = 3,max = 10,message = "Password should be contain min 3 and max 10 ")
	private String password;
	private String role;
	
}
