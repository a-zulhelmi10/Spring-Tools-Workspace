package com.camdrilee.io;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
	private String imgUrl;
	private String catId;
	private String catName;
	private String location;
	private String bgColor;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer totalItem;
}
