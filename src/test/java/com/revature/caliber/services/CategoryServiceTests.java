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
		// Category Service tests setup
		Category c1 = new Category(1, "Java", true, "Sara");
		Category c2 = new Category(2, "SQL", true, "John");
		Category c3 = new Category(3, "Servlets", true, "Ryan");
		Category c4 = new Category(4, "Mockito", false, "Jacob");
		Category c5 = new Category(5, "Java", true, "Josh");
		
//		categoryList.add(c1);
//		categoryList.add(c2);
//		categoryList.add(c3);
//		categoryList.add(c4);
		
		when(categoryService.createCategory(c1)).thenReturn(c1);
		when(categoryService.createCategory(c1)).thenReturn(null);
	}
	
	@Test
	public void createCategoryPass() {
		//categoryService.createCategory(new Category(1, "Java", true, "Sara"));
		assertEquals(new Category(1, "Java", true, "Sara"), categoryService.createCategory(new Category(1, "Java", true, "Sara")));
		
	}

	@Test
	public void testCreateAssessmentWithAlreadyExistingCategory() {
		assertEquals(null, categoryService.createCategory(new Category(1, "Java", true, "Sara")));
	}
}
