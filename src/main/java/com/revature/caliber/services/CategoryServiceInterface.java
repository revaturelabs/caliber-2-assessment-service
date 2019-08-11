package com.revature.caliber.services;

import com.revature.caliber.beans.Category;

import java.util.List;

import org.springframework.stereotype.Service;
import com.revature.caliber.dto.CategoryDTO;

@Service
public interface CategoryServiceInterface {

	Category createCategory(CategoryDTO categoryDTO);
	Category updateCategory(CategoryDTO categoryDTO);
	List<Category> listAllCategories ();
}
