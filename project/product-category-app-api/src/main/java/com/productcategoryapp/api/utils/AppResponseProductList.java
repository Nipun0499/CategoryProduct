package com.productcategoryapp.api.utils;

import java.util.List;

import com.productcategoryapp.api.payloads.ProductDto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AppResponseProductList {
	
	private List<ProductDto> c;
	private String message;
	private boolean success;
	

}