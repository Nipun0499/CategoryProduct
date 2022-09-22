package com.productcategoryapp.api.payloads;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	
	private int category_id;
	List<ProductDto> products;

	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 25, message="Name should be minimum 3 letters and maximum 25 letters")
	private String category_name;
	@NotEmpty(message = "Description is mandatory")
	@Size(min = 5, max = 100,message="Description should be minimum 5 letters and maximum 100 letters")
	private String category_desc;
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
