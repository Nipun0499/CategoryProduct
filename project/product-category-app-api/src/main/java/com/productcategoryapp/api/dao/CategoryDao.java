package com.productcategoryapp.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.productcategoryapp.api.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer>{

	
	List<Category> findByIsDeletedIsFalse();


}
