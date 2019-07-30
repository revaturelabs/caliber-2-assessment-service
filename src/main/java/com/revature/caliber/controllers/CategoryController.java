package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Category;
import com.revature.caliber.services.CategoryService;

@RestController
@RequestMapping("categories")
@CrossOrigin("*")
public class CategoryController {
	
private Logger log = Logger.getLogger("CategoryController.class");
    
    @Autowired
    private CategoryService categoryService;

    //Get create request, send to service layer
    @PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    	System.out.println("Create Category" + category);
    	
    	Category cat = categoryService.createCategory(category);
    	if(cat == null) return new ResponseEntity<>(cat, HttpStatus.BAD_REQUEST);
    	return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }
}
