package com.revature.caliber.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;

public class CategoryServiceTests {

	CategoryService categoryService = mock(CategoryService.class);
	List<Category> categoryList = new ArrayList<>();

	@Before
	public void setup() {
		CategoryDTO c1 = new CategoryDTO(1, "Java", true, "Sara");
		Category c11 = new Category(1, "Java", true, "Sara");
		CategoryDTO cd1 = new CategoryDTO(2, "Big data", true, "Ryan");
		Category cd11 = new Category(2, "Big data", true, "Ryan");
		CategoryDTO cd2 = new CategoryDTO(3, "Big data", true, "Ryan");

		when(categoryService.createCategory(c1)).thenReturn(c11);
		when(categoryService.createCategory(c1)).thenReturn(null);
		when(categoryService.updateCategory(cd1)).thenReturn(cd11);
		when(categoryService.updateCategory(cd2)).thenReturn(null);

		Category cl1 = new Category(1, "Java", true, "Sara");
		Category cl2 = new Category(2, "SQL", true, "John");
		Category cl3 = new Category(3, "Servlets", true, "Ryan");
		Category cl4 = new Category(4, "Mockito", false, "Jacob");

		categoryList.add(cl1);
		categoryList.add(cl2);
		categoryList.add(cl3);
		categoryList.add(cl4);

		when(categoryService.listAllCategories()).thenReturn(categoryList);
	}

	@Test
	public void createCategoryPass() {
		assertEquals(null, categoryService.createCategory(new CategoryDTO(1, "Java", true, "Sara")));
	}

	@Test
	public void testCreateAssessmentWithAlreadyExistingCategory() {
		assertEquals(null, categoryService.createCategory(new CategoryDTO(1, "Java", true, "Sara")));
	}

	@Test
	public void testUpdateCategoryAlreadyExists() {
		assertEquals(null, categoryService.updateCategory(new CategoryDTO(2, "Big data", true, "Ryan")));
	}

	@Test
	public void testUpdateCategoryDoesNotExist() {
		assertEquals(null, categoryService.updateCategory(new CategoryDTO(3, "Big data", true, "Ryan")));
	}

	@Test
	public void testListAllCategories() {
		assertEquals(categoryList, categoryService.listAllCategories());
	}
}
