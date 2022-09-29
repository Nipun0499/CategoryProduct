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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {
	
	public Category(int categoryId, List<Product> products, String categoryName, String categoryDesc, Date createDate,
			Date updateDate) {
		super();
		this.categoryId = categoryId;
		this.products = products;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isActive = true;
		this.isDeleted = false;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="c_id",referencedColumnName="categoryId")
	List<Product> products;
	private String categoryName;
	private String categoryDesc;
	private Date createDate;
	private Date updateDate;
	private boolean isActive;
	private boolean isDeleted;	
}
