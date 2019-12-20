package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.caliber.beans.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByCategoryId(int id);

	@Query("select c from Category c where UPPER(c.skillCategory) = :skillCategory")
	Category findBySkillCategory(@Param("skillCategory") String skillCategory);

	@Override
	List<Category> findAll();
}
