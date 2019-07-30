package com.revature.caliber.services;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;

@Service
public interface CategoryServiceInterface {

	Category createCategory(CategoryDTO categoryDTO);
}
