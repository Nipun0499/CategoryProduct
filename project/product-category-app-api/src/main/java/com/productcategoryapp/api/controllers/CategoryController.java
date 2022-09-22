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
import com.productcategoryapp.api.services.*;
import com.productcategoryapp.api.utils.AppResponseCategory;
import com.productcategoryapp.api.utils.AppResponseCategoryList;
import com.productcategoryapp.api.payloads.CategoryDto;

@RestController
//@RequestMapping("/productcategory")
public class CategoryController {
		
	@Autowired
	private CategoryService c;
	
	@GetMapping("/category")
	public ResponseEntity<AppResponseCategoryList> getCategory() throws Exception
	{
		try
		{
			return new ResponseEntity<>
				(new AppResponseCategoryList(this.c.getCategory(),"Here are all Categories!",true)
						,HttpStatus.FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/category/{CategoryId}")
	public ResponseEntity<AppResponseCategory> getCategory(@Valid @PathVariable String CategoryId) throws Exception
	{
		try
		{
			return new ResponseEntity<>
				(new AppResponseCategory(this.c.getCategory(Integer.parseInt(CategoryId)),"Here is your category!",
						true),HttpStatus.FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/category")
	public ResponseEntity<AppResponseCategory> addCategory(@Valid @RequestBody CategoryDto category) throws Exception
	{
		try
		{
			return new ResponseEntity<>
				(new AppResponseCategory(c.addCategory(category),"New Category Created",true)
						,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/category")
	public ResponseEntity<AppResponseCategory> updateCategory(@Valid @RequestBody CategoryDto category) throws Exception
	{
		try
		{
			return new ResponseEntity<>
				(new AppResponseCategory(c.updateCategory(category),
						"Category updated if present otherwise created a new category!",true),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/category/{CategoryId}")
	public ResponseEntity<AppResponseCategory> deleteCategory(@Valid @PathVariable String CategoryId) throws Exception
	{
		try
		{
			return new ResponseEntity<>
				(new AppResponseCategory(c.deleteCategory(Integer.parseInt(CategoryId)),
						"Category Deleted!",true),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
			
}
