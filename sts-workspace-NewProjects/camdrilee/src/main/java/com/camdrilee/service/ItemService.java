package com.camdrilee.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camdrilee.entity.CategoryEntity;
import com.camdrilee.entity.ItemEntity;
import com.camdrilee.io.ItemRequest;
import com.camdrilee.io.ItemResponse;
import com.camdrilee.repository.CategoryRepository;
import com.camdrilee.repository.ItemRepository;


@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private CategoryRepository catRepo;
	@Autowired
	private FileUploadService fileUpload;

	public ItemResponse addItem(ItemRequest itemReq,MultipartFile file) {
		ItemEntity newItem =  new ItemEntity();
		newItem = convertToEntity(itemReq);
		String imgUrl = fileUpload.uploadFile(file);
		newItem.setImgUrl(imgUrl);
		itemRepo.save(newItem);
		return convertToItemRes(newItem);
	}

	private ItemResponse convertToItemRes(ItemEntity newItem) {
		return ItemResponse.builder()
			.itemId(newItem.getItemId())
			.itemName(newItem.getItemName())
			.quantity(newItem.getQuantity())
			.catEntity(newItem.getCatEntity())
			.description(newItem.getDescription())
			.createdAt(newItem.getCreatedAt())
			.updatedAt(newItem.getUpdatedAt())
			.imgUrl(newItem.getImgUrl())
			.bgColor(newItem.getCatEntity().getBgColor())
			.build();
	}

	private ItemEntity convertToEntity(ItemRequest itemReq) {
		return ItemEntity.builder()
		.itemId(UUID.randomUUID().toString())
		.itemName(itemReq.getItemName())
		.quantity(itemReq.getQuantity())
		.description(itemReq.getDescription())
		.catEntity(catRepo.findByCatId(itemReq.getCatId()).orElseThrow(()->new RuntimeException("Category not found")))
		.build();
	}
	
	public void deleteItem(String itemId) {
		ItemEntity existingItem = itemRepo.findByItemId(itemId).orElseThrow(()->new RuntimeException("Item Not Found")); 
		itemRepo.delete(existingItem);
	}

	public List<ItemResponse> listItem() {
		return itemRepo.findAll().stream().map(itemEntity->convertToItemRes(itemEntity)).collect(Collectors.toList());
	}
	public List<ItemResponse> listItemByCat(String catId){
		CategoryEntity currentCat = catRepo.findByCatId(catId).orElseThrow(()->new RuntimeException("Category not found"));
		return itemRepo.findByCatEntity(currentCat).stream().map(itemEntity->convertToItemRes(itemEntity)).collect(Collectors.toList());
	}
}
