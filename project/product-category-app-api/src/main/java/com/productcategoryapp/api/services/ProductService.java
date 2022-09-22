package com.productcategoryapp.api.services;

import java.util.List;

import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.ProductDto;


public interface ProductService {
	public List<ProductDto> getProduct();
	public ProductDto getProduct(int ProductId);
	public ProductDto addProduct(ProductDto p);
	public ProductDto updateProduct(ProductDto p);
	public ProductDto deleteProduct(int ProductId);
	

}
