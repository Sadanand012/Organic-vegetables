package com.organic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	@Column(unique = true)
	private String name;
	@NotEmpty
	private String type;
	@NotNull
	@NotEmpty
	private Double price;
	@NotNull
	@NotEmpty
	private Integer quantity;
	
}
