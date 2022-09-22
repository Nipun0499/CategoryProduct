package com.productcategoryapp.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.productcategoryapp.api.entity.Product;



@Repository
public interface ProductDao extends JpaRepository<Product,Integer>{

}
