package com.billingsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.billingsoftware.io.CategoryRequest;
import com.billingsoftware.io.CategoryResponse;
import com.billingsoftware.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@RequestMapping(value = "/categories") //no need to use after apply the security
//@CrossOrigin(value = "http://localhost:4200") //since we have used security config so no need to use cross origin
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/admin/categories")
	@ResponseStatus(HttpStatus.CREATED)
	/* before
	public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
	*/
	public CategoryResponse addCategory(@RequestPart("category") String categoryString, @RequestPart("file") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();
		CategoryRequest request = null;
		try {
			request = objectMapper.readValue(categoryString, CategoryRequest.class);
			return categoryService.add(request, file);
		}catch (JsonProcessingException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exception occured while parsing the json: " +ex.getMessage());
		}
	}

	@GetMapping("/categories")
	public List<CategoryResponse> fetchCategories() {
		return categoryService.getCategory();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/admin/categories/{categoryId}")
	public void remove(@PathVariable String categoryId) {
		try {
			categoryService.delete(categoryId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
}
