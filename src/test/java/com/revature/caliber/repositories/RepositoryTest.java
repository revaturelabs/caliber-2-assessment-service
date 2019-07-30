package com.revature.caliber.repositories;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Category;

public class RepositoryTest {

	CategoryRepository categoryRepository = mock(CategoryRepository.class);
	List<Category> categoryList = new ArrayList<>();

	@Before
	public void setup() {
		// Category Repository tests setup
		Category c1 = new Category(1, "Java", true, "Sara");
		Category c2 = new Category(2, "SQL", true, "John");
		Category c3 = new Category(3, "Servlets", true, "Ryan");
		Category c4 = new Category(4, "Mockito", false, "Jacob");
		Category c5 = new Category(5, "Java", true, "Josh");
		
		categoryList.add(c1);
		categoryList.add(c2);
		categoryList.add(c3);
		categoryList.add(c4);
		
//		//when(categoryRepository.findAllCategories()).thenReturn(categoryList);
//		when(categoryRepository.findCategoryById(3)).thenReturn(c3);
//		when(categoryRepository.findCategoryById(1)).thenReturn(null);
//		when(categoryRepository.findCategoryById(5)).thenReturn(c1);
		when(categoryRepository.save(c1)).thenReturn(c1);
		when(categoryRepository.save(c5)).thenReturn(null);
	}
	
	//Testing if things in list
//	@Test
//	public void testFindAllCategoriesMethod() {
//		assertThat(categoryRepository.findAllCategories(), hasItems(
//				new Category(1, "Java", true, "Sara"),
//				new Category(2, "SQL", true, "John"),
//				new Category(3, "Servlets", true, "Ryan"),
//				new Category(4, "Mockito", false, "Jacob")
//		));
//	}
	
	//Testing if things in list
//	@Test
//	public void testFindAllCategoriesMethodFail() {
//		assertNotEquals(categoryRepository.findAllCategories(), hasItems(
//				new Category(6, "Spring", false, "Naresh")
//		));
//	}
	
	//Check for ID
//	@Test
//	public void testFindId() {
//		assertEquals(null, categoryRepository.findCategoryById(1));
//	}
	
	//Create new category
	@Test
	public void testCreateCategory() {
		assertEquals(new Category(1, "Java", true, "Sara"), categoryRepository.save(new Category(1, "Java", true, "Sara")));
	}
	
	//Create same category 
	@Test
	public void testCreateCategoryNull() {
		assertEquals(null, categoryRepository.save(new Category(5, "Java", true, "Josh")));
	}

	//Find category by id, using a good input
//	@Test
//	public void testFindCategoryByIdWithExpectedInput() {
//		assertEquals(new Category(3, "Servlets", true, "Ryan"), categoryRepository.findCategoryById(3));
//	}
	
//	//Find category by id, using a bad input
//	@Test
//	public void testFindCategoryByIdWithBadInput() {
//		assertEquals(null, categoryRepository.findCategoryById(6));
//	}
}

