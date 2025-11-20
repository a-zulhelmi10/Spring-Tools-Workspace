package com.camdrilee.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
	//private String imgUrl;
	private String name;
	private String location;
	private String bgColor;
	
}
//•	Picture, category,location,background-color