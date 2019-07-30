package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryId(Integer id);
	Category findCategoryBySkillCategory(String skillCategory);
}
