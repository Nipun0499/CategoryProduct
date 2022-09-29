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
import com.productcategoryapp.api.controllers.ProductController;
import com.productcategoryapp.api.dao.ProductDao;
import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.ProductDto;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	private ModelMapper modelMapper=new ModelMapper();
	Logger logger=LoggerFactory.getLogger(ProductController.class);


	
	// This methods return all the list of Product whose deleted status is not deleted
	@Override
	public List<ProductDto> getProduct() {
		
		
		List<Product> p=productDao.findByIsDeletedIsFalse();
		List<ProductDto> pdto=new ArrayList<>();;
		for(Product i : p)
			pdto.add(productToProductDto(i));
		if(pdto.isEmpty())
			throw new NoResourceFoundException("Product");
		else
		{
			logger.info("Product List found!");
			return pdto;
		}
	}

	
	
	
	// This methods return a particular product whose product Id is passed if exists in the database
	@Override
	public ProductDto getProduct(int productId) {
		Optional<Product> p=productDao.findById(productId);
		Product p1=p.orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));
		logger.info("Product found!");
		return productToProductDto(p1);
		
	}

	
	
	//This method adds a new product to the database whose object is passed in the parameter
	@Override
	public ProductDto addProduct(ProductDto p) {
		Product x=productDtoToProduct(p);
		productDao.save(x);
		logger.info("Product added!");
		return productToProductDto(x);
	}



	//This methods update an existing product 
	@Override
	public ProductDto updateProduct(ProductDto p) {
		
		
		Product x=productDtoToProduct(p);
		productDao.save(x);
		logger.info("Product updated!");
		return productToProductDto(x);
	}



	
	//This method deletes an existing product based on the productId passed
	@Override
	public ProductDto deleteProduct(int productId) {
		Optional<Product> p=productDao.findById(productId);
		Product p1=p.orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));
		p1.setDeleted(true);
		p1.setActive(false);
		productDao.save(p1);
		logger.info("Product deleted!");
		return productToProductDto(p1);
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
