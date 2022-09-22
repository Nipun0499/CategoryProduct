package com.productcategoryapp.api.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Category c;
	private String product_name;
	private String product_brand;
	private String product_desc;
	private long price;
	private Date createDate;
	private Date updateDate;
	private boolean isActive;
	private boolean isDeleted;	
	
	
}
