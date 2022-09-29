package com.productcategoryapp.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import com.productcategoryapp.api.Exceptions.NoResourceFoundException;
import com.productcategoryapp.api.Exceptions.ResourceNotFoundException;
import com.productcategoryapp.api.controllers.CategoryController;
import com.productcategoryapp.api.dao.CategoryDao;
import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.CategoryDto;
import com.productcategoryapp.api.payloads.ProductDto;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	private ModelMapper modelMapper=new ModelMapper();
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);


	
	
	
	// This methods return all the list of Category whose deleted status is not deleted
	@Override
	public List<CategoryDto> getCategory() {
		
		List<Category> c=categoryDao.findByIsDeletedIsFalse();
		List<CategoryDto> cdto=new ArrayList<>();;
		for(Category i : c)
			cdto.add(categoryToCategoryDto(i));
		if(cdto.isEmpty())
			throw new NoResourceFoundException("Category");
		else
		{
			logger.info("Category List found!");
			return cdto;
		}
	}

	
	
	
	
	
	// This methods return a particular category whose category Id is passed if exists in the database
	@Override
	public CategoryDto getCategory(int categoryId) {
		Optional<Category> c=categoryDao.findById(categoryId);
		Category c1=c.orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
		logger.info("Category found!");
		return categoryToCategoryDto(c1);
	}

	
	
	
	
	
	
	//This method adds a new category to the database whose object is passed in the parameter
	@Override
	public CategoryDto addCategory(CategoryDto c) throws HttpMessageNotReadableException{
		
		Category x=categoryDtoToCategory(c);
		categoryDao.save(x);
		logger.info("Category added!");
		return categoryToCategoryDto(x);
	}

	
	
	
	
	
	//This methods update an existing category 
	@Override
	public CategoryDto updateCategory(CategoryDto c) {
		
		Category x=categoryDtoToCategory(c);
		categoryDao.save(x);
		logger.info("Category updated!");
		return categoryToCategoryDto(x);
	}



	//This method deletes an existing category based on the CategoryId passed
	@Override
	public CategoryDto deleteCategory(int categoryId) {
		Optional<Category> p=categoryDao.findById(categoryId);
		Category p1=p.orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
		p1.setDeleted(true);
		p1.setActive(false);
		categoryDao.save(p1);
		logger.info("Category deleted!");
		return categoryToCategoryDto(p1);
	}
	
	
	
	
	//This object converts CategoryDto to Category using ModelMapper 
	public Category categoryDtoToCategory(CategoryDto p)
	{
		Category category=this.modelMapper.map(p,Category.class);
		return category;
	}
	
	
	
	
	//This object converts Category to CategoryDto using ModelMapper 
	public CategoryDto categoryToCategoryDto(Category p)
	{
		CategoryDto category=this.modelMapper.map(p, CategoryDto.class);
		return category;
	}
	
	
	
	
	//This object converts ProductDto to Product using ModelMapper 
	public Product productDtoToProduct(ProductDto p)
	{
		Product product=this.modelMapper.map(p,Product.class);
		return product;
	}
	
	
	
	
	//This object converts Product to ProductDto using ModelMapper 
	public ProductDto productToProductDto(Product p)
	{
		ProductDto product=this.modelMapper.map(p,ProductDto.class);
		return product;
	}

}
