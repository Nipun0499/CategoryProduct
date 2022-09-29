package com.productcategoryapp.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcategoryapp.api.payloads.ProductDto;
import com.productcategoryapp.api.services.ProductService;
import com.productcategoryapp.api.utils.AppResponseProduct;
import com.productcategoryapp.api.utils.AppResponseProductList;

@RestController
public class ProductController {
	

	@Autowired
	private ProductService p;
	
	
	//GET Request to get the list of all products
	@GetMapping("/product")
	public ResponseEntity<AppResponseProductList> getProduct()
	{
		
			return new ResponseEntity<>
			(new AppResponseProductList(this.p.getProduct(),"Here are all Products!",true),HttpStatus.FOUND);
		
	}
	
	
	
	//GET request to get a particular product based on ID
	@GetMapping("/product/{productId}")
	public ResponseEntity<AppResponseProduct> getProduct(@Valid @PathVariable String productId)
	{
	
			return new ResponseEntity<>
			(new AppResponseProduct(this.p.getProduct(Integer.parseInt(productId)),
					"Here is your Product!",true),HttpStatus.FOUND);
	}
	
	
	
	//POST request to add a new Product
	@PostMapping("/product")
	public ResponseEntity<AppResponseProduct> addProduct(@Valid @RequestBody ProductDto product)
	{
			return new ResponseEntity<>
			(new AppResponseProduct(p.addProduct(product),"New Product Created!",true),HttpStatus.CREATED);
		
	}
	
	
	
	//PUT request to update an existing product
	@PutMapping("/product")
	public ResponseEntity<AppResponseProduct> updateProduct(@Valid @RequestBody ProductDto product)
	{
			return new ResponseEntity<>
			(new AppResponseProduct(p.updateProduct(product),"New Product Created!",true),HttpStatus.OK);
	}	
	
	
	
	
	//DELETE request to delete a particular product based on ID
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<AppResponseProduct> deleteProduct(@Valid @PathVariable String productId)
	{

			return new ResponseEntity<>
			(new AppResponseProduct(p.deleteProduct(Integer.parseInt(productId)),
					"Product Deleted Successfully!",true),HttpStatus.OK);
	}


}
