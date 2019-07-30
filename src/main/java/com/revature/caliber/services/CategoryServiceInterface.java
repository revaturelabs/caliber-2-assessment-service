package com.revature.caliber.services;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;

@Service
public interface CategoryServiceInterface {

	Category createCategory(Category category);
}
