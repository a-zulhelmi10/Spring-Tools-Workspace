package com.spring.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //apply lombok annotation
@AllArgsConstructor
@Data //setter,getter,tostring
public class Product {
	private int productId;
	private String productName;
	private double productprice;
}
