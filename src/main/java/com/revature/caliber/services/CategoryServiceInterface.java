package com.revature.caliber.services;

import com.revature.caliber.beans.Category;
import org.springframework.stereotype.Service;
import com.revature.caliber.dto.CategoryDTO;

@Service
public interface CategoryServiceInterface {

	Category createCategory(CategoryDTO categoryDTO);
	Category updateCategory(CategoryDTO categoryDTO);
}
