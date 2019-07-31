package com.revature.caliber.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.converter.CategoryConverter;
import com.revature.caliber.dto.CategoryDTO;
import com.revature.caliber.exceptions.DoesNotExistException;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.services.CategoryService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryServiceMock;

	@InjectMocks
	CategoryController categoryController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateCategory() throws Exception {
		CategoryDTO catObj = new CategoryDTO();
		catObj.setCategoryOwner("Sara");
		catObj.setSkillCategory("Test123");
		catObj.setActive(true);
		Category catObj1 = CategoryConverter.convert(catObj);
		when(categoryServiceMock.createCategory(any(CategoryDTO.class))).thenReturn(catObj1);

		String catJson = new ObjectMapper().writeValueAsString(catObj);
		mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content(catJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.categoryOwner").value("Sara"))
				.andExpect(jsonPath("$.skillCategory").value("Test123"))
				.andExpect(jsonPath("$.active").value(true));
	}

	@Test
	public void testCreateCategoryFail() throws Exception {
		Category catObj = new Category();
		catObj.setCategoryOwner("Sara");
		catObj.setSkillCategory("Test123");
		catObj.setActive(true);
		when(categoryServiceMock.createCategory(any(CategoryDTO.class)))
				.thenThrow(new DuplicateException("Skill type already exists"));

		String catJson = new ObjectMapper().writeValueAsString(catObj);
		mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content(catJson))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Skill type already exists"));
	}
	
	@Test
	public void testEditCategory()throws Exception{
		CategoryDTO cDTO = new CategoryDTO();
		cDTO.setCategoryOwner("Ryan");
		cDTO.setSkillCategory("Testing");
		cDTO.setActive(true);
		Category c = CategoryConverter.convert(cDTO);
		when(categoryServiceMock.updateCategory(any(CategoryDTO.class))).thenReturn(c);
		
		String catJson = new ObjectMapper().writeValueAsString(c);
		mockMvc.perform(put("/categories/update").contentType(MediaType.APPLICATION_JSON).content(catJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.categoryOwner").value("Ryan"))
				.andExpect(jsonPath("$.skillCategory").value("Testing"))
				.andExpect(jsonPath("$.active").value(true));
	}
	
	@Test
	public void testEditCategoryFail()throws Exception{
		CategoryDTO cDTO = new CategoryDTO();
		cDTO.setCategoryOwner("Ryan");
		cDTO.setSkillCategory("Testing");
		cDTO.setActive(true);
		Category c = CategoryConverter.convert(cDTO);
		when(categoryServiceMock.updateCategory(any(CategoryDTO.class)))
				.thenThrow(new DoesNotExistException("Category does not already exist"));
		
		String catJson = new ObjectMapper().writeValueAsString(c);
		mockMvc.perform(put("/categories/update").contentType(MediaType.APPLICATION_JSON).content(catJson))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Category does not already exist"));
	}
}
