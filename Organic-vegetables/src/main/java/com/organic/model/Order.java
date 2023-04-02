package com.organic.model;



import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	private LocalDate date;
	
//	make customer and billing relation
	
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "order")
//	@JsonIgnore
//	private Cart cart;
//	
	@Embedded
	@ElementCollection
	@JsonIgnore
	private List<VegetableDTO> vegetableList=new ArrayList<>();
	
	
}
