package com.productcategoryapp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.productcategoryapp.api.controllers.ProductController;
import com.productcategoryapp.api.dao.ProductDao;
import com.productcategoryapp.api.entity.Product;
import com.productcategoryapp.api.payloads.ProductDto;
import com.productcategoryapp.api.services.ProductService;

@ComponentScan(basePackages= "com.productcategoryapp.api")
@AutoConfigureMockMvc
@SpringBootTest(classes= {ProductControllerTests.class})
@ContextConfiguration
public class ProductControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	ProductService c;
	
	@Mock
	ProductDao cd;
	
	
	@InjectMocks
	ProductController cc;
	
	
    
    ModelMapper modelMapper=new ModelMapper();
	
	@BeforeEach
	public void setUp()
	{
		mockMvc=MockMvcBuilders.standaloneSetup(cc).build();
	}

	@Test
	public void getAllProductTest() throws Exception
	{
		List<Product> list1=new ArrayList<Product>();
		Product p1 = new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        Product p2 = new Product(1, null,"Monitor","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
	    list1.add(p1);
	    list1.add(p2);
	    List<ProductDto> list= list1.stream().map((i)->modelMapper.map(i,ProductDto.class))
	    		.collect(Collectors.toList());
	    List<ProductDto> list2=list.stream()
	    		.filter((i)->i.isDeleted()==false)
	    		.collect(Collectors.toList());
	    when(cd.findAll()).thenReturn(list1);
        when(c.getProduct()).thenReturn(list2);
        this.mockMvc.perform(get("/product"))
        		.andExpect(status().isFound())
        		.andDo(print());
	}
	
	
	@Test
	public void getProductTest() throws Exception
	{
		List<Product> list=new ArrayList<Product>();
		Product p1 = new Product(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
	    list.add(p1);
	    ProductDto p2=modelMapper.map(p1, ProductDto.class);
	    when(cd.findById(1)).thenReturn(Optional.of(p1));
        when(c.getProduct(1)).thenReturn(p2);
        this.mockMvc.perform(get("/product/{product_id}",1))
        		.andExpect(status().isFound())
        		.andExpect(MockMvcResultMatchers.jsonPath(".product_id").value(1))
        		.andExpect(MockMvcResultMatchers.jsonPath(".product_name").value("Laptop"))
        		.andDo(print());
	}
	
	@Test
	public void postProductTest() throws Exception
	{
		ProductDto p = new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
		Product p1 = modelMapper.map(p, Product.class);
		ProductDto p2=modelMapper.map(p1, ProductDto.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonBody = mapper.writeValueAsString(p2);
		Mockito.when(cd.save(p1)).thenReturn(p1);
		Mockito.when(c.addProduct(p)).thenReturn(p2);
		assertThat(c.addProduct(p)).isEqualTo(p2);
        this.mockMvc.perform(post("/product")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonBody))
        		.andExpect(status().isCreated())
       		.andDo(print());
	}
	
	@Test
	public void putProductTest() throws Exception
	{
		ProductDto p = new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
		//Category p1 = modelMapper.map(p, Category.class);
		//CategoryDto p2=modelMapper.map(p1, CategoryDto.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonBody = mapper.writeValueAsString(p);
        this.mockMvc.perform(put("/product")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andDo(print());
	}
	
	
	@Test
	public void deleteProductTest() throws Exception
	{
		ProductDto p1 = new ProductDto(1, null,"Laptop","HP", "This category is of Laptop",100000, new Date(), new Date(), true, false);
        ProductDto p2 = new ProductDto(1, null,"Monitor","HP", "This category is of Laptop",100000, new Date(), new Date(),false,true);
		when(c.deleteProduct(1)).thenReturn(p2);
        this.mockMvc.perform(delete("/product/{product_id}",1))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath(".active").value(false))
        		.andExpect(MockMvcResultMatchers.jsonPath(".deleted").value(true))
        		.andDo(print());
	}
	
	
	
}
