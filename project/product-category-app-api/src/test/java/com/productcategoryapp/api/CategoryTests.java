package com.productcategoryapp.api;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import com.productcategoryapp.api.Exceptions.ResourceNotFoundException;
import com.productcategoryapp.api.dao.CategoryDao;
import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.CategoryDto;
import com.productcategoryapp.api.payloads.ProductDto;
import com.productcategoryapp.api.services.CategoryServiceImpl;
import java.util.ArrayList;
import com.productcategoryapp.api.Exceptions.NoResourceFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
class CategoryTests {

    @Mock
    private CategoryDao repo1;
    
    @InjectMocks
    private CategoryServiceImpl service1;
    
    ModelMapper modelMapper=new ModelMapper();
    
    
    
    @Test
    void getCategoryTest() {
        Category p1 = new Category(1, null,"Laptop","This category is of Laptop", new Date(), new Date());
        Category p2 = new Category(1, null,"Hello","This category is of Laptop", new Date(), new Date());
        List<Category> list = new ArrayList<Category>();
        list.add(p1);
        list.add(p2);
        List<CategoryDto> l=new ArrayList<>();
        for(Category i: list)
        	l.add(modelMapper.map(i, CategoryDto.class));

        Mockito.when(repo1.findAll()).thenReturn(list);
        assertThat(service1.getCategory()).isEqualTo(l);
    }
    
    

    @Test
    void getCategoryByIdTest() {
        Category p = new Category(1, null,"Laptop","This category is of Laptop", new Date(), new Date());
        CategoryDto p1 = modelMapper.map(p, CategoryDto.class);
        Mockito.when(repo1.findById(1)).thenReturn(Optional.of(p));
        assertThat(service1.getCategory(1)).isEqualTo(p1);
    }
    
    
    
    @Test
    void updateCategoryTest() {
        CategoryDto p = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(2022,8,1), new Date());
        Category p2=modelMapper.map(p, Category.class);
        Mockito.when(repo1.save(p2)).thenReturn(p2);
        assertThat(service1.updateCategory(p)).isEqualTo(p);
    }
    
   
    
    @Test
    void addCategoryTest() {
        CategoryDto p = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(), new Date());
        Category p2=modelMapper.map(p,Category.class);
        Mockito.when(repo1.save(p2)).thenReturn(p2);
        assertThat(service1.addCategory(p)).isEqualTo(p);
    }
    
    
    
    @Test
    void deleteCategoryTest() {
        Category p = new Category(1, null,"Laptop","This category is of Laptop", new Date(), new Date());
        Mockito.when(repo1.findById(1)).thenReturn(Optional.of(p));
        CategoryDto p1=modelMapper.map(p,CategoryDto.class);
        p1.setActive(false);
        p1.setDeleted(true);
        assertThat(service1.deleteCategory(1)).isEqualTo(p1);
    }
    
    
    
    @Test
    void getCategoryTest1() {
    	List<Product>list1=new ArrayList<>();
    	Product p11=new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,1), new Date(2022,9,10));
    	Product p12=new Product(1, null,"Desktop","HP", "This category is of Laptop",100000, new Date(), new Date());
    	list1.add(p11);
    	list1.add(p12);
        Category p1 = new Category(1, list1,"Laptop","This category is of Laptop", new Date(), new Date());
        Category p2 = new Category(1, null,"Hello","This category is of Laptop", new Date(), new Date());
        List<Category> list = new ArrayList<Category>();
        list.add(p1);
        list.add(p2);
        List<CategoryDto> l=new ArrayList<>();
        for(Category i: list)
        	l.add(modelMapper.map(i, CategoryDto.class));

        Mockito.when(repo1.findAll()).thenReturn(list);
        assertThat(service1.getCategory()).isEqualTo(l);
    }
    
    

    @Test
    void getCategoryByIdTest1() {
    	List<Product>list=new ArrayList<>();
    	Product p11=new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,1), new Date(2022,9,10));
    	Product p12=new Product(1, null,"Desktop","HP", "This category is of Laptop",100000, new Date(), new Date());
    	list.add(p11);
    	list.add(p12);
        Category p = new Category(1, list,"Laptop","This category is of Laptop", new Date(2022,7,12), new Date());
        CategoryDto p1 = modelMapper.map(p, CategoryDto.class);
        Mockito.when(repo1.findById(1)).thenReturn(Optional.of(p));
        assertThat(service1.getCategory(1)).isEqualTo(p1);
    }
    
    
    
    @Test
    void updateCategoryTest1() {
    	List<ProductDto>list=new ArrayList<>();
    	ProductDto p11=new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,1), new Date(2022,9,10));
    	ProductDto p12=new ProductDto(1, null,"Desktop","HP", "This category is of Laptop",100000, new Date(), new Date());
    	list.add(p11);
    	list.add(p12);
        CategoryDto p = new CategoryDto(1, list,"Laptop","This category is of Laptop", new Date(2022,8,1), new Date());
        Category p2=modelMapper.map(p, Category.class);
        Mockito.when(repo1.save(p2)).thenReturn(p2);
        assertThat(service1.updateCategory(p)).isEqualTo(p);
    }
    
   
    
    @Test
    void addCategoryTest1() {
    	List<ProductDto>list=new ArrayList<>();
    	ProductDto p11=new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,1), new Date(2022,9,10));
    	ProductDto p12=new ProductDto(1, null,"Desktop","HP", "This category is of Laptop",100000, new Date(), new Date());
    	list.add(p11);
    	list.add(p12);
        CategoryDto p = new CategoryDto(1, list,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date());
        Category p2=modelMapper.map(p,Category.class);
        Mockito.when(repo1.save(p2)).thenReturn(p2);
        assertThat(service1.addCategory(p)).isEqualTo(p);
    }
    
    
    
    @Test
    void deleteCategoryTest1() {
    	List<Product>list=new ArrayList<>();
    	Product p11=new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(2022,9,1), new Date(2022,9,10));
    	Product p2=new Product(1, null,"Desktop","HP", "This category is of Laptop",100000, new Date(), new Date());
    	list.add(p11);
    	list.add(p2);
    	Category p = new Category(1,list,"Laptop","This category is of Laptop", new Date(2022,5,6), new Date());
        Mockito.when(repo1.findById(1)).thenReturn(Optional.of(p));
        CategoryDto p1=modelMapper.map(p,CategoryDto.class);
        p1.setActive(false);
        p1.setDeleted(true);
        assertThat(service1.deleteCategory(1)).isEqualTo(p1);
    }
    
    
    @Test
    void getCategoryTest2() {
    	List<Category> p = new ArrayList<>();
        Mockito.when(repo1.findAll()).thenReturn(p);
        assertThrows(NoResourceFoundException.class,()-> service1.getCategory());
    }
    
    

    @Test
    void getCategoryByIdTest2() {
    	Optional<Category> p = Optional.empty();
        Mockito.when(repo1.findById(1)).thenReturn(p);
        assertThrows(ResourceNotFoundException.class,()-> service1.getCategory(1));
    }
    
    
    
 
    void deleteCategoryTest3()  throws ResourceNotFoundException {
    	Optional<Category> p = Optional.empty();
        Mockito.when(repo1.findById(1)).thenReturn(p); 
        assertThrows(ResourceNotFoundException.class,()-> service1.deleteCategory(1));
    }
}
