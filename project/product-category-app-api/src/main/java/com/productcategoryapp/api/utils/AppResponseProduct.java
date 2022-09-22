package com.productcategoryapp.api.utils;

import com.productcategoryapp.api.payloads.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AppResponseProduct {
	
	private ProductDto p;
	private String message;
	private boolean success;
	

}
