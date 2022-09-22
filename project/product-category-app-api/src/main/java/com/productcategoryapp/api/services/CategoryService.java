package com.productcategoryapp.api.services;

import java.util.List;

import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.payloads.CategoryDto;


public interface CategoryService {
	public List<CategoryDto> getCategory();
	public CategoryDto getCategory(int categoryId);
	public CategoryDto addCategory(CategoryDto c);
	public CategoryDto updateCategory(CategoryDto c);
	public CategoryDto deleteCategory(int CategoryId);
	

}
