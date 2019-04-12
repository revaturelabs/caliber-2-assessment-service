package com.revature.caliber.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.services.AssessmentService;

@RestController
@CrossOrigin("*")
public class AssessmentController {
    
    private Logger log = Logger.getLogger("AssessmentController.class");
    
    @Autowired
    private AssessmentService as;
    
    @GetMapping("/all/assessment/all")
    public List<Assessment> findAllAssessments(){
        log.debug("Inside getAllAssessments");
        return as.findAllAssessments();
    }
    
    @GetMapping("/all/assessment/{id}")
    public Assessment findAssessmentById(@PathVariable("id") Integer id){
        log.debug("Inside findAssessmentById");
        return as.findAssessmentById(id);
    }
    
    @PostMapping(value="/all/assessment/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) {
    	as.createAssessment(assessment);
    	return new ResponseEntity<>(assessment, HttpStatus.CREATED);
    }
    
}