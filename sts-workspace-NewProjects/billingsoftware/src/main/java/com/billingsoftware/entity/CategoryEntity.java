package com.billingsoftware.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String categoryId;
	@Column(unique = true)
	private String name;
	private String description;
	private String bgColor;
	private String imgUrl;
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
}
