package com.organic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderNo;
	private Integer customerId;
	private Double totalAmount;
	private String status;
	
//	make customer and billing relation
	
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "order")
//	@JsonIgnore
//	private Cart cart;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vegetable> vegetableList=new ArrayList<>();
	

}
