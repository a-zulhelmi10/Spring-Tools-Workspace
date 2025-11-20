package com.billingsoftware.io;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ItemRequest {
	private String name;
	private BigDecimal price;
	private String categoryId; //provide to repository so that we can retrieve the categoryEntity from table category(means from categoryId we can get category at itemEntity
	private String description;
	
}
