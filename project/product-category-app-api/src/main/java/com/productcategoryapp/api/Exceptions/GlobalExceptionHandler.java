package com.productcategoryapp.api.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.productcategoryapp.api.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//Handles ResourceNotFoundException which is raised when we are trying to find any category or product which don't exist
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	//Handles MethodArgumentNotValidException which is raised when the argument fails the validation constraints
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String,String> m=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->
		{
			String field=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			m.put(field, message);
		});
		
		return new ResponseEntity<Map<String,String>>(m,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	//Handles HttpMessageNotReadableException which is raised when data is not entered in the correct format
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String,String>> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex)
	{
		String s=ex.getCause().getMessage();
		String message="Enter the data in correct format!";
		Map<String,String> m=new HashMap<>();
		m.put( message,s);
		
		return new ResponseEntity<Map<String,String>>(m,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	//Handles NoResourceNotFoundException which is raised when no entity is found
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiResponse> NoCategoryFoundExceptionHandler(NoResourceFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
	

}
