package com.organic.model;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vegetable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vegId;
	private String name;
	private String type;
	private Double price;
	private Integer quantity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderNo")
	@JsonIgnore
	private Order order;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId")
	@JsonIgnore
	private Cart cart;
	
	
	// added by Akash
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vegId") // it will save to create extra column
	private Set<Feedback> feedbacks;
		
}
