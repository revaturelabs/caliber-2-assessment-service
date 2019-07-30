package com.revature.caliber.repositories;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Category;

public class RepositoryTest {

	CategoryRepository categoryRepository = mock(CategoryRepository.class);
	List<Category> categoryList = new ArrayList<>();

	@Before
	public void setup() {
		Category c1 = new Category(1, "Java", true, "Sara");
		Category c2 = new Category(2, "SQL", true, "John");
		Category c3 = new Category(3, "Servlets", true, "Ryan");
		Category c4 = new Category(4, "Mockito", false, "Jacob");
		Category c5 = new Category(5, "Java", true, "Josh");
		
		categoryList.add(c1);
		categoryList.add(c2);
		categoryList.add(c3);
		categoryList.add(c4);
		
		when(categoryRepository.save(c1)).thenReturn(c1);
		when(categoryRepository.save(c5)).thenReturn(null);
	}
	

	@Test
	public void testCreateCategory() {
		assertEquals(new Category(1, "Java", true, "Sara"), categoryRepository.save(new Category(1, "Java", true, "Sara")));
	}
	
	@Test
	public void testCreateCategoryNull() {
		assertEquals(null, categoryRepository.save(new Category(5, "Java", true, "Josh")));
	}
}

