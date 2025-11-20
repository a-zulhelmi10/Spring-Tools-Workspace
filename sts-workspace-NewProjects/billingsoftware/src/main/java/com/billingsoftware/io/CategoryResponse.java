package com.billingsoftware.io;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	private String categoryId;
	private String name;
	private String description;
	private String bgColor;
	private String imgUrl;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer itemCount; //for displaying item numbers purchased
}

