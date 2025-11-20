package com.billingsoftware.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.billingsoftware.entity.ItemEntity;
import com.billingsoftware.entity.CategoryEntity;
import com.billingsoftware.io.ItemRequest;
import com.billingsoftware.io.ItemResponse;
import com.billingsoftware.repository.CategoryRepository;
import com.billingsoftware.repository.ItemRepository;

@Service
public class ItemService {

    private final AuthenticationManager authenticationManager;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ItemRepository itemRepository;

    ItemService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	public ItemResponse add(ItemRequest itemRequest,MultipartFile file) {
			String imgUrl = fileUploadService.uploadFile(file);
			ItemEntity newItem = convertIntoEntity(itemRequest);
			CategoryEntity existingCategory = categoryRepository.findByCategoryId(itemRequest.getCategoryId())
					.orElseThrow(() ->new RuntimeException("Category not found: " +itemRequest.getCategoryId()));
			newItem.setCategory(existingCategory);
			newItem.setImgUrl(imgUrl);
			itemRepository.save(newItem);
			return convertToResponse(newItem);
	}
	private ItemResponse convertToResponse(ItemEntity newItem) {
		return ItemResponse.builder()
				.itemId(newItem.getItemId())
				.name(newItem.getName())
				.description(newItem.getDescription())
				.price(newItem.getPrice())
				.categoryId(newItem.getCategory().getCategoryId())
				.categoryName(newItem.getCategory().getName())
				.imgUrl(newItem.getImgUrl())
				.createdAt(newItem.getCreatedAt())
				.updatedAt(newItem.getUpdatedAt())
				.build();
	}
	private ItemEntity convertIntoEntity(ItemRequest itemRequest) {
		return ItemEntity.builder()
			.itemId(UUID.randomUUID().toString())
			.name(itemRequest.getName())
			.description(itemRequest.getDescription())
			.price(itemRequest.getPrice())
			.build();
	}
	public List<ItemResponse>fetchItems(){
		return itemRepository.findAll()
		.stream()
		.map(itemEntity -> convertToResponse(itemEntity))
		.collect(Collectors.toList());
	}
	public void deleteItem(String itemId){
		ItemEntity currentItem = itemRepository.findByItemId(itemId).orElseThrow(
				()-> new RuntimeException("Item not found for item id: " +itemId));
		boolean isFileDelete = fileUploadService.deleteFile(currentItem.getImgUrl());
		if(isFileDelete) {
			itemRepository.delete(currentItem);
		}else
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to delete the image");
	}
	public Integer countItem(Long catId) {
		return itemRepository.countByCategoryId(catId);
	}
}
