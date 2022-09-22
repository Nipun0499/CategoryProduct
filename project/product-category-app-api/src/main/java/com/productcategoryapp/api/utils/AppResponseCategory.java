package com.productcategoryapp.api.utils;

import com.productcategoryapp.api.payloads.CategoryDto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AppResponseCategory {
	
	private CategoryDto c;
	private String message;
	private boolean success;
	

}