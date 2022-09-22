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
	
//	@Autowired
	private ModelMapper modelMapper=new ModelMapper();
	Logger logger=LoggerFactory.getLogger(ProductController.class);


	@Override
	public List<ProductDto> getProduct() {
		
		
		List<Product> p=productDao.findAll().stream()
				.filter(i -> i.isDeleted()==false)
				.collect(Collectors.toList());
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

	@Override
	public ProductDto getProduct(int ProductId) {
		// TODO Auto-generated method stub
		Optional<Product> p=productDao.findById(ProductId);
		Product p1=p.orElseThrow(() -> new ResourceNotFoundException("Product","id",ProductId));
		logger.info("Product found!");
		return productToProductDto(p1);
		
	}

	@Override
	public ProductDto addProduct(ProductDto p) {
		// TODO Auto-generated method stub
		Product x=productDtoToProduct(p);
		productDao.save(x);
		// TODO Auto-generated method stub
		logger.info("Product added!");
		return productToProductDto(x);
	}



	@Override
	public ProductDto updateProduct(ProductDto p) {
		
		
		Product x=productDtoToProduct(p);
		productDao.save(x);
		logger.info("Product updated!");
		// TODO Auto-generated method stub
		return productToProductDto(x);
	}



	@Override
	public ProductDto deleteProduct(int ProductId) {
		Optional<Product> p=productDao.findById(ProductId);
		Product p1=p.orElseThrow(() -> new ResourceNotFoundException("Product","id",ProductId));
		p1.setDeleted(true);
		p1.setActive(false);
		productDao.save(p1);
		logger.info("Product deleted!");
		return productToProductDto(p1);
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
