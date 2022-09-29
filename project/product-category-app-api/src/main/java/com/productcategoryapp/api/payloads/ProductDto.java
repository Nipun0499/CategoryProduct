package com.productcategoryapp.api.payloads;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDto {

	
	private int productId;
	private CategoryDto c;
	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 25, message="Name should be minimum 3 letters and maximum 25 letters")
	private String productName;
	@NotEmpty(message = "Brand is mandatory")
	@Size(min = 1, max = 25,message="Brand should be minimum 1 letter and maximum 25 letters")
	private String productBrand;
	@NotEmpty(message = "Description is mandatory")
	@Size(min = 5, max = 100,message="Description should be minimum 5 letters and maximum 100 letters")
	private String productDesc;
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
	
	public ProductDto(int productId, CategoryDto c,
			@NotEmpty(message = "Name is mandatory") @Size(min = 3, max = 25, message = "Name should be minimum 3 letters and maximum 25 letters") String productName,
			@NotEmpty(message = "Brand is mandatory") @Size(min = 1, max = 25, message = "Brand should be minimum 1 letter and maximum 25 letters") String productBrand,
			@NotEmpty(message = "Description is mandatory") @Size(min = 5, max = 100, message = "Description should be minimum 5 letters and maximum 100 letters") String productDesc,
			@NotNull @Min(value = 10, message = "Price must be greater than 10") long price,
			@NotNull @Past Date createDate, @NotNull @Past Date updateDate) {
		super();
		this.productId = productId;
		this.c = c;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productDesc = productDesc;
		this.price = price;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isActive = true;
		this.isDeleted = false;
	}
}
