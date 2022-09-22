package com.productcategoryapp.api.Exceptions;


public class ResourceNotFoundException extends RuntimeException {
	
	private String resource;
	private String feild;
	private int value;
	
	public ResourceNotFoundException(String resource,String feild,int value)
	{
		super(String.format("%s not found with %s : %s",resource,feild,value));
		this.feild=feild;
		this.resource=resource;
		this.value=value;
	}
	

}
