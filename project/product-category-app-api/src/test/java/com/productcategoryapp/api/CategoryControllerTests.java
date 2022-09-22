package com.productcategoryapp.api;

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
import static org.mockito.ArgumentMatchers.any;
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
import com.productcategoryapp.api.controllers.CategoryController;
import com.productcategoryapp.api.dao.CategoryDao;
import com.productcategoryapp.api.entity.Category;
import com.productcategoryapp.api.payloads.CategoryDto;
import com.productcategoryapp.api.services.CategoryService;

@ComponentScan(basePackages= "com.productcategoryapp.api")
@AutoConfigureMockMvc
@SpringBootTest(classes= {CategoryControllerTests.class})
@ContextConfiguration
public class CategoryControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CategoryService c;
	
	@Mock
	CategoryDao cd;
	
	
	@InjectMocks
	CategoryController cc;
	
	
    
    ModelMapper modelMapper=new ModelMapper();
	
	@BeforeEach
	public void setUp()
	{
		mockMvc=MockMvcBuilders.standaloneSetup(cc).build();
	}

	@Test
	public void getAllCategoryTest() throws Exception
	{
		List<Category> list1=new ArrayList<Category>();
		Category p1 = new Category(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), true, false);
	    Category p2 = new Category(2, null,"Hello","This category is of Laptop", new Date(), new Date(), true, false);
	    list1.add(p1);
	    list1.add(p2);
	    List<CategoryDto> list= list1.stream().map((i)->modelMapper.map(i,CategoryDto.class))
	    		.collect(Collectors.toList());
	    List<CategoryDto> list2=list.stream()
	    		.filter((i)->i.isDeleted()==false)
	    		.collect(Collectors.toList());
	    when(cd.findAll()).thenReturn(list1);
        when(c.getCategory()).thenReturn(list2);
        this.mockMvc.perform(get("/category"))
        		.andExpect(status().isFound())
        		.andDo(print());
	}
	
	
	@Test
	public void getCategoryTest() throws Exception
	{
		List<Category> list=new ArrayList<Category>();
		Category p1 = new Category(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), true, false);
	    list.add(p1);
	    CategoryDto p2=modelMapper.map(p1, CategoryDto.class);
	    when(cd.findById(1)).thenReturn(Optional.of(p1));
        when(c.getCategory(1)).thenReturn(p2);
        this.mockMvc.perform(get("/category/{category_id}",1))
        		.andExpect(status().isFound())
        		.andExpect(MockMvcResultMatchers.jsonPath(".category_id").value(1))
        		.andExpect(MockMvcResultMatchers.jsonPath(".category_name").value("Laptop"))
        		.andDo(print());
	}
	
	@Test
	public void postCategoryTest() throws Exception
	{
		CategoryDto p = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), true, false);
		Category p1 = modelMapper.map(p, Category.class);
		CategoryDto p2=modelMapper.map(p1, CategoryDto.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonBody = mapper.writeValueAsString(p2);
		Mockito.when(cd.save(p1)).thenReturn(p1);
		Mockito.when(c.addCategory(any())).thenReturn(p2);
        this.mockMvc.perform(post("/category")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonBody))
        		.andExpect(status().isCreated())
       		.andDo(print());
	}
	
	@Test
	public void putCategoryTest() throws Exception
	{
		CategoryDto p = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), true, false);
		Category p1 = modelMapper.map(p, Category.class);
		CategoryDto p2=modelMapper.map(p1, CategoryDto.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonBody = mapper.writeValueAsString(p);
        Mockito.when(c.updateCategory(any())).thenReturn(p2);
        this.mockMvc.perform(put("/category")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andDo(print());
	}
	
	
	@Test
	public void deleteCategoryTest() throws Exception
	{
		CategoryDto p1 = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), true, false);
		CategoryDto p2 = new CategoryDto(1, null,"Laptop","This category is of Laptop", new Date(), new Date(), false,true);
		when(c.deleteCategory(1)).thenReturn(p2);
        this.mockMvc.perform(delete("/category/{categroy_id}",1))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath(".active").value(false))
        		.andExpect(MockMvcResultMatchers.jsonPath(".deleted").value(true))
        		.andDo(print());
	}
	
	
	
}
