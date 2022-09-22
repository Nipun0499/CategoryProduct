package com.productcategoryapp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.productcategoryapp.api.Exceptions.NoResourceFoundException;
import com.productcategoryapp.api.Exceptions.ResourceNotFoundException;
import com.productcategoryapp.api.dao.ProductDao;
import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.CategoryDto;
import com.productcategoryapp.api.payloads.ProductDto;
import com.productcategoryapp.api.services.ProductServiceImpl;

@SpringBootTest
public class ProductsTests {
	
	
	@Mock
    private ProductDao repo;
    @InjectMocks
    private ProductServiceImpl service;
    
    ModelMapper modelMapper=new ModelMapper();

    @Test
    void getProductTest() {
        Product p1 = new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        Product p2 = new Product(1, null,"Monitor","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        List<ProductDto> l=new ArrayList<>();
        for(Product i: list)
        	l.add(modelMapper.map(i, ProductDto.class));

        Mockito.when(repo.findAll()).thenReturn(list);
        assertThat(service.getProduct()).isEqualTo(l);
    }
    
    

    @Test
    void getProductByIdTest() {
        Product p1=new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
    	ProductDto p2=modelMapper.map(p1, ProductDto.class);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(p1));
        assertThat(service.getProduct(1)).isEqualTo(p2);
    }

    
    
    
    @Test
    void updateProductTest() {
        ProductDto p = new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        Product p2=modelMapper.map(p, Product.class);
        Mockito.when(repo.save(p2)).thenReturn(p2);
        assertThat(service.updateProduct(p)).isEqualTo(p);
    }
    
    

    @Test
    void addProductTest() {
        ProductDto p = new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        Product p2=modelMapper.map(p,Product.class);
        Mockito.when(repo.save(p2)).thenReturn(p2);
        assertThat(service.addProduct(p)).isEqualTo(p);
    }
    
    
    
    
    @Test
    void deleteProductTest() {
        Product p = new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(p));
        ProductDto p1=modelMapper.map(p,ProductDto.class);
        p1.setActive(false);
        p1.setDeleted(true);
        assertThat(service.deleteProduct(1)).isEqualTo(p1);
    }
    
    @Test
    void getProductTest1() {
    	Category cs = new Category(1,null,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date(), true, false);
        Product p1 = new Product(1, cs,"Laptop","HP", "This category is of Laptop",100000, new Date(2019,8,10), new Date(2022,7,10), true, false);
        Product p2 = new Product(1, cs,"Monitor","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        List<ProductDto> l=new ArrayList<>();
        for(Product i: list)
        	l.add(modelMapper.map(i, ProductDto.class));

        Mockito.when(repo.findAll()).thenReturn(list);
        assertThat(service.getProduct()).isEqualTo(l);
    }
    
    

    @Test
    void getProductByIdTest1() {
    	Category cs = new Category(1,null,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date(), true, false);
        Product p1=new Product(1, cs,"Laptop","HP", "This category is of Laptop",100000, new Date(2020,12,9), new Date(), true, false);
    	ProductDto p2=modelMapper.map(p1, ProductDto.class);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(p1));
        assertThat(service.getProduct(1)).isEqualTo(p2);
    }

    
    
    
    @Test
    void updateProductTest1() {
    	CategoryDto cs = new CategoryDto(1,null,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date(), true, false);
        ProductDto p = new ProductDto(1, cs,"Laptop","HP", "This category is of Laptop",100000, new Date(2021,9,11), new Date(), true, false);
        Product p2=modelMapper.map(p, Product.class);
        Mockito.when(repo.save(p2)).thenReturn(p2);
        assertThat(service.updateProduct(p)).isEqualTo(p);
    }
    
    

    @Test
    void addProductTest1() {
    	CategoryDto cs = new CategoryDto(1,null,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date(), true, false);
        ProductDto p = new ProductDto(1, cs,"Laptop","HP", "This category is of Laptop",100000, new Date(2021,12,9), new Date(), true, false);
        Product p2=modelMapper.map(p,Product.class);
        Mockito.when(repo.save(p2)).thenReturn(p2);
        assertThat(service.addProduct(p)).isEqualTo(p);
    }
    
    
    
    
    @Test
    void deleteProductTest1() {
    	Category cs = new Category(1,null,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date(), true, false);
        Product p = new Product(1, cs,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,18), new Date(), true, false);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(p));
        ProductDto p1=modelMapper.map(p,ProductDto.class);
        p1.setActive(false);
        p1.setDeleted(true);
        assertThat(service.deleteProduct(1)).isEqualTo(p1);
    }
    
    @Test
    void getProductTest2() {
    	List<Product> p = new ArrayList<>();
        Mockito.when(repo.findAll()).thenReturn(p);
        assertThrows(NoResourceFoundException.class,()-> service.getProduct());
    }
    
    

    @Test
    void getProductByIdTest2() {
    	Optional<Product> p = Optional.empty();
        Mockito.when(repo.findById(1)).thenReturn(p);
        assertThrows(ResourceNotFoundException.class,()-> service.getProduct(1));
    }
    
    
    
 
    void deleteProductTest3()  throws ResourceNotFoundException {
    	Optional<Product> p = Optional.empty();
        Mockito.when(repo.findById(1)).thenReturn(p); 
        assertThrows(ResourceNotFoundException.class,()-> service.deleteProduct(1));
    }

}
