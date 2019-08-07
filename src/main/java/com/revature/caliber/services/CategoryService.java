package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;
import com.revature.caliber.exceptions.CategoryNullException;
import com.revature.caliber.exceptions.DoesNotExistException;
import com.revature.caliber.converter.CategoryConverter;
import com.revature.caliber.dto.CategoryDTO;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.exceptions.OwnerNullException;
import com.revature.caliber.repositories.CategoryRepository;
import static com.revature.caliber.services.ErrorConstants.*;

@Service
public class CategoryService implements CategoryServiceInterface{

	Logger log = Logger.getLogger("CategoryService.class"); 
	 
	@Autowired
	private CategoryRepository categoryRepository;
	

	public Category createCategory(CategoryDTO categoryDTO) {
		Category category = CategoryConverter.convert(categoryDTO);
		category.setSkillCategory(category.getSkillCategory().trim().toUpperCase());
		if(category.getSkillCategory() == null || category.getSkillCategory().trim().equals("")) 
		{
			throw new CategoryNullException(CATEGORY_CANNOT_BE_NULL);
		}
		Category categoryObj = categoryRepository.findBySkillCategory(category.getSkillCategory().toUpperCase());

		if (categoryObj != null)
			throw new DuplicateException(SKILL_TYPE_ALREADY_EXISTS);
		else 
			return categoryRepository.save(category);
	}

	public Category updateCategory(CategoryDTO categoryDTO) {
		Category category = CategoryConverter.convert(categoryDTO);
		category.setSkillCategory(category.getSkillCategory().trim());
		if(category.getSkillCategory() == null || category.getSkillCategory().trim().equals("")) 
		{
			throw new CategoryNullException(CATEGORY_CANNOT_BE_NULL);
		}
		category.setCategoryOwner(category.getCategoryOwner().trim());
		if(category.getCategoryOwner() == null || category.getCategoryOwner().trim().equals("")) 
		{
			throw new OwnerNullException(OWNER_CANNOT_BE_NULL);
		}
		Category categoryObj = categoryRepository.findByCategoryId(category.getCategoryId());
		if(categoryObj == null) {
			throw new DoesNotExistException(CATEGORY_DOES_NOT_EXIST);
		}
		else {
			return categoryRepository.save(category);
		}
	}
	
	public List<Category> listAllCategories(){
		List <Category> cList = categoryRepository.findAll();
		if(cList == null) {
			throw new DoesNotExistException("No categories exist");
		}
		else {
			return cList;
		}
	}
}
