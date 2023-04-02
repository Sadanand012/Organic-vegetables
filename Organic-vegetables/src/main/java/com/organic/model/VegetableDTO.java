package com.organic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VegetableDTO {

	private Integer vegId;
	private String name;
	private Double price;
	private Integer quantity;
	
}
