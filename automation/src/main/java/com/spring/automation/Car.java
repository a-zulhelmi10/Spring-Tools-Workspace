package com.spring.automation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CarModels")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="owner")
	private String owner;
	@Column(name="car_brand")
	private String brand;
	@Column(name="car_model")
	private String model;
	@Column(name="price")
	private double price;
	@Column(name="initial_deposit")
	private double depo;
	@Column(name="total_months_loans")
	private int months;
}
