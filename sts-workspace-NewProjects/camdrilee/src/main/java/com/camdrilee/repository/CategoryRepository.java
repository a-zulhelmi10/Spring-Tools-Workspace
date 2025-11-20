package com.camdrilee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camdrilee.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{
	Optional<CategoryEntity>findByCatId(String catId);
	
}
