package com.organic.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@NotNull
	private String customerName;
	@NotNull
	private String mobileNumber;
	@Email
	private String emailId;
	@NotNull
	@Size(min = 3,max = 10,message = "Password should be contain min 3 and max 10 ")
	private String password;
	
	@Embedded
	private Address address;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private BillingDetails billingDetails;

}
