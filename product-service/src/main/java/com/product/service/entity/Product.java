package com.product.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = " Product name is required")
	private String name;
	@NotBlank(message = " Product description is required ")
	private String description;
	@Positive(message = " Product price must be greater than Zero ")
	private double price;
	@PositiveOrZero(message = " Quantity cannot be negative ")
	private int quantity;

}
