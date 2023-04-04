package com.organic.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@NotNull
	private String flatNo;
	@NotNull
	private String buildingName;
	@NotNull
	private String area;
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	@Min(value = 3,message = "pin should be minimum 3 number")
	private String pincode;
	
}
