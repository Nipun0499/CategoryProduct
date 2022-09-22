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
//@RequestMapping("/productcategory")
public class ProductController {
	

	@Autowired
	private ProductService p;
	
	@GetMapping("/product")
	public ResponseEntity<AppResponseProductList> getProduct()
	{
		try
		{
			return new ResponseEntity<>
			(new AppResponseProductList(this.p.getProduct(),"Here are all Products!",true),HttpStatus.FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/product/{ProductId}")
	public ResponseEntity<AppResponseProduct> getProduct(@Valid @PathVariable String ProductId)
	{
		try
		{
			return new ResponseEntity<>
			(new AppResponseProduct(this.p.getProduct(Integer.parseInt(ProductId)),
					"Here is your Product!",true),HttpStatus.FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<AppResponseProduct> addProduct(@Valid @RequestBody ProductDto product)
	{
		try
		{
			return new ResponseEntity<>
			(new AppResponseProduct(p.addProduct(product),"New Product Created!",true),HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/product")
	public ResponseEntity<AppResponseProduct> updateProduct(@Valid @RequestBody ProductDto product)
	{
		try
		{
			return new ResponseEntity<>
			(new AppResponseProduct(p.updateProduct(product),"New Product Created!",true),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}	
	
	@DeleteMapping("/product/{ProductId}")
	public ResponseEntity<AppResponseProduct> deleteProduct(@Valid @PathVariable String ProductId)
	{
		try
		{
			return new ResponseEntity<>
			(new AppResponseProduct(p.deleteProduct(Integer.parseInt(ProductId)),
					"Product Deleted Successfully!",true),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
