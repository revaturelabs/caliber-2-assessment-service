package com.revature.caliber.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategoryDTOTests {

	@Test
	public void testCategoryDTO() {

		CategoryDTO c = new CategoryDTO();
		c.setActive(true);
		c.setCategoryId(3);
		c.setCategoryOwner("Sara");
		c.setSkillCategory("Java");

		assertEquals(true, c.isActive());
		assertEquals(3, c.getCategoryId());
		assertEquals("Sara", c.getCategoryOwner());
		assertEquals("Java", c.getSkillCategory());
	}

	@Test
	public void testCategoryDTOConstructor() {

		CategoryDTO c = new CategoryDTO(3, "Java", true, "Sara");

		assertEquals(true, c.isActive());
		assertEquals(3, c.getCategoryId());
		assertEquals("Sara", c.getCategoryOwner());
		assertEquals("Java", c.getSkillCategory());
	}
}