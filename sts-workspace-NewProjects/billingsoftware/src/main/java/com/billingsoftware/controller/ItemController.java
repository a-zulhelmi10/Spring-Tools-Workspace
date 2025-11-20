package com.billingsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.billingsoftware.io.ItemRequest;
import com.billingsoftware.io.ItemResponse;
import com.billingsoftware.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/admin/item")
	public ItemResponse addItem(@RequestPart("item") String itemString,@RequestPart("file") MultipartFile file) { //"item" and "file" is a key (key & value pairs
		ObjectMapper objmp = new ObjectMapper();
		ItemRequest itemRequest = null;
		try {
			itemRequest = objmp.readValue(itemString, ItemRequest.class);
			return itemService.add(itemRequest, file);
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error occured while processing the Json");
		}
	}
	
	@GetMapping("/items")
	public List<ItemResponse> readItems(){
		return itemService.fetchItems();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("admin/item/{itemId}")
	public void delete(@PathVariable String itemId) {
		try {
			itemService.deleteItem(itemId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found");
		}
	}
	@GetMapping("/items/{catId}")
	public Integer countItem(@PathVariable Long catId) {
		return itemService.countItem(catId);
	}
	
}
