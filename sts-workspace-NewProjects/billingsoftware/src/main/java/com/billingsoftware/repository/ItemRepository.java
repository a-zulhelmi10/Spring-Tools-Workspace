package com.billingsoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billingsoftware.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
	Optional<ItemEntity> findByItemId(String itemId);

	Integer countByCategoryId(Long catId); //to count items in each category
}
