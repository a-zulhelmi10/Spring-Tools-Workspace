package com.billingsoftware.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.billingsoftware.entity.CategoryEntity;
import com.billingsoftware.io.CategoryRequest;
import com.billingsoftware.io.CategoryResponse;
import com.billingsoftware.repository.CategoryRepository;
import com.billingsoftware.repository.ItemRepository;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private ItemRepository itemRepository;
	
	public CategoryResponse add(CategoryRequest categoryRequest, MultipartFile file) {
		String imgUrl = fileUploadService.uploadFile(file);
		CategoryEntity newCategory = convertToEntity(categoryRequest);
		newCategory.setImgUrl(imgUrl);
		newCategory = categoryRepository.save(newCategory);
		return convertToResponse(newCategory);
	}
	
	public List<CategoryResponse> getCategory(){
		return categoryRepository.findAll()
			.stream()
			.map(categoryEntity -> convertToResponse(categoryEntity))
			.collect(Collectors.toList());
	}
	
	public void delete(String categoryId) {
		CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(() 
				-> new RuntimeException("Category no found: " +categoryId));
		fileUploadService.deleteFile(existingCategory.getImgUrl());
		categoryRepository.delete(existingCategory);
	}
	
	private CategoryResponse convertToResponse(CategoryEntity newCategory) {
		Integer itemsCount = itemRepository.countByCategoryId(newCategory.getId());
		return CategoryResponse.builder()
		.categoryId(newCategory.getCategoryId())
		.name(newCategory.getName())
		.description(newCategory.getDescription())
		.bgColor(newCategory.getBgColor())
		.imgUrl(newCategory.getImgUrl())
		.createdAt(newCategory.getCreatedAt())
		.updatedAt(newCategory.getUpdatedAt())
		.itemCount(itemsCount)
		.build();
		       
	}
	private CategoryEntity convertToEntity(CategoryRequest categoryRequest) {
		return CategoryEntity.builder()
		.categoryId(UUID.randomUUID().toString())
		.name(categoryRequest.getName())
		.description(categoryRequest.getDescription())
		.bgColor(categoryRequest.getBgColor())
		.build();
	}
}
