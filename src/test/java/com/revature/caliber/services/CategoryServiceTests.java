package com.revature.caliber.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;

public class CategoryServiceTests {
	
	CategoryService categoryService = mock(CategoryService.class);
	
	@Before
	public void setup() {
		CategoryDTO c1 = new CategoryDTO(1, "Java", true, "Sara");
		Category c11 = new Category(1, "Java", true, "Sara");
		
		when(categoryService.createCategory(c1)).thenReturn(c11);
		when(categoryService.createCategory(c1)).thenReturn(null);
	}
	
	@Test
	public void createCategoryPass() {
		assertEquals(null, categoryService.createCategory(new CategoryDTO(1, "Java", true, "Sara")));
		
	}

	@Test
	public void testCreateAssessmentWithAlreadyExistingCategory() {
		assertEquals(null, categoryService.createCategory(new CategoryDTO(1, "Java", true, "Sara")));
	}
}
