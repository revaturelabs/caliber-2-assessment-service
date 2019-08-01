package com.revature.caliber.converter;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;

public class CategoryConverter {
	
	private CategoryConverter() {}

	public static CategoryDTO convert(Category c)
	{
		CategoryDTO category = new CategoryDTO();
		category.setCategoryId(c.getCategoryId());
		category.setActive(c.isActive());
		category.setCategoryOwner(c.getCategoryOwner());
		category.setSkillCategory(c.getSkillCategory());
		return category;
		
	}
	
	public static Category convert(CategoryDTO c)
	{
		Category category = new Category();
		category.setCategoryId(c.getCategoryId());
		category.setActive(c.isActive());
		category.setCategoryOwner(c.getCategoryOwner());
		category.setSkillCategory(c.getSkillCategory());
		return category;
		
	}
}
