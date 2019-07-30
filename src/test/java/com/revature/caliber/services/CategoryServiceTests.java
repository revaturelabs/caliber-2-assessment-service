package com.revature.caliber.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Category;

public class CategoryServiceTests {
	
	CategoryService categoryService = mock(CategoryService.class);
	
	@Before
	public void setup() {
		Category c1 = new Category(1, "Java", true, "Sara");
		
		when(categoryService.createCategory(c1)).thenReturn(c1);
		when(categoryService.createCategory(c1)).thenReturn(null);
	}
	
	@Test
	public void createCategoryPass() {
		assertEquals(null, categoryService.createCategory(new Category(1, "Java", true, "Sara")));
		
	}

	@Test
	public void testCreateAssessmentWithAlreadyExistingCategory() {
		assertEquals(null, categoryService.createCategory(new Category(1, "Java", true, "Sara")));
	}
}
