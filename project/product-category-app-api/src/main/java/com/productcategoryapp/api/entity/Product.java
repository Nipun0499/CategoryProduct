package com.productcategoryapp.api.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@ManyToOne(cascade=CascadeType.ALL)
	private Category c;
	private String productName;
	private String productBrand;
	private String productDesc;
	private long price;
	private Date createDate;
	private Date updateDate;
	private boolean isActive;
	private boolean isDeleted;
	public Product(int productId, Category c, String productName, String productBrand, String productDesc, long price,
			Date createDate, Date updateDate) {
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
