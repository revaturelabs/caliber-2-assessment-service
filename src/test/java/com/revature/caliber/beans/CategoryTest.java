package com.revature.caliber.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategoryTest {

	@Test
	public void testCategory() {

		Category c = new Category();
		c.setActive(false);
		c.setCategoryId(4);
		c.setCategoryOwner("Bill");
		c.setSkillCategory("SQL");

		assertEquals(false, c.isActive());
		assertEquals(4, c.getCategoryId());
		assertEquals("Bill", c.getCategoryOwner());
		assertEquals("SQL", c.getSkillCategory());
	}

	@Test
	public void testCategoryConstructor() {

		Category c = new Category(3, "Java", true, "Sara");

		assertEquals(true, c.isActive());
		assertEquals(3, c.getCategoryId());
		assertEquals("Sara", c.getCategoryOwner());
		assertEquals("Java", c.getSkillCategory());
	}
}
