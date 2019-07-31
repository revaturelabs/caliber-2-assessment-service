package com.revature.caliber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryId(Integer id);
	Category findBySkillCategory(String skillCategory);
	//Category findCategoryBySkillCategory(String skillCategory);
}
