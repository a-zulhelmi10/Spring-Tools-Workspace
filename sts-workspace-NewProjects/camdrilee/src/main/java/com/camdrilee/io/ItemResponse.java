package com.camdrilee.io;

import java.sql.Timestamp;

import com.camdrilee.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {
	private String itemId;
	private String imgUrl;
	private String itemName;
	private String bgColor;
	private double quantity;
	private CategoryEntity catEntity;
	private String description;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
