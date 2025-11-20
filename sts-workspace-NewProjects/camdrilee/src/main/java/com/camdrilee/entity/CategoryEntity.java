package com.camdrilee.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column (unique = true)
	private String catId;
	private String imgUrl;
	@Column (unique = true)
	private String catName;
	private String location;
	private String bgColor;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
}
