package com.productcategoryapp.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="c_id",referencedColumnName="category_id")
	List<Product> products;
	private String category_name;
	private String category_desc;
	private Date createDate;
	private Date updateDate;
	private boolean isActive;
	private boolean isDeleted;	
}
