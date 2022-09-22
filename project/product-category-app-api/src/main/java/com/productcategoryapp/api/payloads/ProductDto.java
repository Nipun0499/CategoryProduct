package com.productcategoryapp.api.payloads;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.productcategoryapp.api.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	
	private int product_id;
	private CategoryDto c;
	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 25, message="Name should be minimum 3 letters and maximum 25 letters")
	private String product_name;
	@NotEmpty(message = "Brand is mandatory")
	@Size(min = 1, max = 25,message="Brand should be minimum 1 letter and maximum 25 letters")
	private String product_brand;
	@NotEmpty(message = "Description is mandatory")
	@Size(min = 5, max = 100,message="Description should be minimum 5 letters and maximum 100 letters")
	private String product_desc;
	@NotNull
	@Min(value = 10, message = "Price must be greater than 10")
	private long price;
	@NotNull
	@Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	@NotNull
	@Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	private boolean isActive;
	private boolean isDeleted;
	
}
