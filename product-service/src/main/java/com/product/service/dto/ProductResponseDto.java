package com.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponseDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;

}
