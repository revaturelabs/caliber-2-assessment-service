package com.revature.caliber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findBySkillCategory(String skillCategory);
}
