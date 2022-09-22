package com.productcategoryapp.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	//@Autowired
	private ModelMapper modelMapper=new ModelMapper();
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);


	@Override
	public List<CategoryDto> getCategory() {
		
		List<Category> c=categoryDao.findAll().stream()
				.filter(i -> i.isDeleted()==false)
				.collect(Collectors.toList());
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

	@Override
	public CategoryDto getCategory(int CategoryId) {
		// TODO Auto-generated method stub
		Optional<Category> c=categoryDao.findById(CategoryId);
		Category c1=c.orElseThrow(() -> new ResourceNotFoundException("Category","id",CategoryId));
		logger.info("Category found!");
		return categoryToCategoryDto(c1);
	}

	@Override
	public CategoryDto addCategory(CategoryDto c) {
		Category x=categoryDtoToCategory(c);
		categoryDao.save(x);
		logger.info("Category added!");
		return categoryToCategoryDto(x);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto c) {
		
		Category x=categoryDtoToCategory(c);
		categoryDao.save(x);
		logger.info("Category updated!");
		return categoryToCategoryDto(x);
	}



	@Override
	public CategoryDto deleteCategory(int CategoryId) {
		// TODO Auto-generated method stub
		Optional<Category> p=categoryDao.findById(CategoryId);
		Category p1=p.orElseThrow(() -> new ResourceNotFoundException("Category","id",CategoryId));
		p1.setDeleted(true);
		p1.setActive(false);
		categoryDao.save(p1);
		logger.info("Category deleted!");
		return categoryToCategoryDto(p1);
	}
	
	public Category categoryDtoToCategory(CategoryDto p)
	{
		Category category=this.modelMapper.map(p,Category.class);
		return category;
	}
	public CategoryDto categoryToCategoryDto(Category p)
	{
		CategoryDto category=this.modelMapper.map(p, CategoryDto.class);
		return category;
	}
	public Product productDtoToProduct(ProductDto p)
	{
		Product product=this.modelMapper.map(p,Product.class);
		return product;
	}
	public ProductDto productToProductDto(Product p)
	{
		ProductDto product=this.modelMapper.map(p,ProductDto.class);
		return product;
	}

}
