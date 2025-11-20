package com.camdrilee.io;

import com.camdrilee.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequest {
	private String itemName;
	private double quantity;
	private String catId;
	private String description;
}
