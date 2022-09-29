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
public class CategoryController {
		
	@Autowired
	private CategoryService c;
	
	
	// GET Request to get list of all Category
	@GetMapping("/category")
	public ResponseEntity<AppResponseCategoryList> getCategory()
	{
			return new ResponseEntity<>
				(new AppResponseCategoryList(this.c.getCategory(),"Here are all Categories!",true)
						,HttpStatus.FOUND);
	}
	
	
	
	
	//GET Request for getting a particular category based on Id
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<AppResponseCategory> getCategory(@Valid @PathVariable String categoryId)
	{
			return new ResponseEntity<>
				(new AppResponseCategory(this.c.getCategory(Integer.parseInt(categoryId)),"Here is your category!",
						true),HttpStatus.FOUND);
	}
	
	
	
	
	//POST Request to add new Category
	@PostMapping("/category")
	public ResponseEntity<AppResponseCategory> addCategory(@Valid @RequestBody CategoryDto category)
	{
			return new ResponseEntity<>
				(new AppResponseCategory(c.addCategory(category),"New Category Created",true)
						,HttpStatus.CREATED);
	}
	
	
	
	
	//PUT Request to update existing category
	@PutMapping("/category")
	public ResponseEntity<AppResponseCategory> updateCategory(@Valid @RequestBody CategoryDto category)
	{
			return new ResponseEntity<>
				(new AppResponseCategory(c.updateCategory(category),
						"Category updated if present otherwise created a new category!",true),HttpStatus.OK);
	}
	
	
	
	
	// DELETE Request to delete a category based on Id
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<AppResponseCategory> deleteCategory(@Valid @PathVariable String categoryId) 
	{
				return new ResponseEntity<>
				(new AppResponseCategory(c.deleteCategory(Integer.parseInt(categoryId)),
						"Category Deleted!",true),HttpStatus.OK);
	}
			
}
