package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.services.CategoryService;

@RestController
@CrossOrigin("*")
public class CategoryController {
	
private Logger log = Logger.getLogger("CategoryController.class");
    
    @Autowired
    private CategoryService categoryService;

}
