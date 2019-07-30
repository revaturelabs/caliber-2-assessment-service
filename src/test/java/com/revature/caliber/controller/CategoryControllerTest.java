package com.revature.caliber.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
		Category catObj = new Category();
		catObj.setCategoryOwner("Sara");
		catObj.setSkillCategory("Test");
		catObj.setActive(true);
		when(categoryServiceMock.createCategory((Category) any(Category.class))).thenReturn(catObj);

		String catJson = new ObjectMapper().writeValueAsString(catObj);
		mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(catJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("nareshkumarh@live.com"));

	}
}
