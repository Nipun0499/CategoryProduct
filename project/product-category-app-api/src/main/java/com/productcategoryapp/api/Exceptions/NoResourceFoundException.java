package com.productcategoryapp.api.Exceptions;


public class NoResourceFoundException extends RuntimeException {
	
	String s;
	public NoResourceFoundException(String s)
	{
		super(String.format("No %s exist! Try Creating new %s!",s,s));
		this.s=s;
	}
	

}
