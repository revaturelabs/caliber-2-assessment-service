package com.revature.caliber.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;

public class CategoryConverterTest {

	@Test
	public void testConvertToDTO()  {
		
		Category c = new Category();
		
		c.setActive(true);
		c.setCategoryId(3);
		c.setCategoryOwner("Sara");
		c.setSkillCategory("Java");
		
		CategoryConverter.convert(c);
		
		assertEquals(true, c.isActive());
		assertEquals(3, c.getCategoryId());
		assertEquals("Sara", c.getCategoryOwner());
		assertEquals("Java", c.getSkillCategory());
	}
	
	@Test
	public void testConvertFromDTO()  {
		
		CategoryDTO c = new CategoryDTO(3,"Java",true,"Sara");
		
		CategoryConverter.convert(c);
		assertEquals(true, c.isActive());
		assertEquals(3, c.getCategoryId());
		assertEquals("Sara", c.getCategoryOwner());
		assertEquals("Java", c.getSkillCategory());
	}
}
