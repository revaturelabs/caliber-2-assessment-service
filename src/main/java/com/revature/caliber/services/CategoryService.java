package com.revature.caliber.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.repositories.CategoryRepository;

@Service
public class CategoryService implements CategoryServiceInterface{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category createCategory(Category category) {
		category = categoryRepository.findCategoryBySkillCategory(category.getSkillCategory());
		if (category != null)
			throw new DuplicateException("Skill category already exists");
		else 
			return categoryRepository.save(category);

	}

	
}
