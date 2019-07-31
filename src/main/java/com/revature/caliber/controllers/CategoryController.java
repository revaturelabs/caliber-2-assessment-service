package com.revature.caliber.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Category;
import com.revature.caliber.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Category;
import com.revature.caliber.dto.CategoryDTO;
import com.revature.caliber.services.CategoryService;

@RestController
@RequestMapping("categories")
@CrossOrigin("*")
public class CategoryController {

    
    @Autowired
    private CategoryService categoryService;

    /**
     * 
     * Create category assessment
     * 
     */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
    	
    	Category cat = categoryService.createCategory(categoryDTO);
    	return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }
    
    /**
     * Update assessment category
     * @param category
     * @return
     */
    @PutMapping(value="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDTO category) {
      Category temp = categoryService.updateCategory(category);
      if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
	}
    
    /**
     * Get a list of all assessment categories
     * @return
     */
    @GetMapping(value="/list")
    public ResponseEntity<List<Category>> listAllCategories(){
    	List<Category> cList = categoryService.listAllCategories();
    	if(cList == null) return new ResponseEntity<>(cList, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(cList, HttpStatus.OK);
    }
}
