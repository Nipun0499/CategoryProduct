package com.productcategoryapp.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.payloads.CategoryDto;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer>{

}
