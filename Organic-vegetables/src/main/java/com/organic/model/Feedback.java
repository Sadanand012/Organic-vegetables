package com.organic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;
	@NotEmpty
	private Integer customerId;
	@NotEmpty
	private Integer vegId;
	@NotNull
	@Size(message = "Rating should be 1-5 only")
	@Pattern(regexp = "[1-5]")
	private Double rating;
	@NotNull
	private String comments;
	
	
	
}
