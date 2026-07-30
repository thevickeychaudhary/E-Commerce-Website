package com.product.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductRequestDto {

	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@Positive
	private double price;
	@PositiveOrZero
	private Integer quantity;

}
