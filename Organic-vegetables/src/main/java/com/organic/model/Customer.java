package com.organic.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;


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
	@NotNull
	private String emailId;
	@NotNull
	private String password;
	
	@Embedded
	private Address address;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private BillingDetails billingDetails;
	
//	@JsonIgnore
//	@OneToOne(cascade = CascadeType.ALL)
//	private Order order;

}
