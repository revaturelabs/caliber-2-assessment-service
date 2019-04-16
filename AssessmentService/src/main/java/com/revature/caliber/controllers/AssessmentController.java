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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<Assessment>> findAllAssessments(){
        log.debug("Inside getAllAssessments");
        List<Assessment> temp = as.findAllAssessments();
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/assessment/batch/{id}")
    public ResponseEntity<List<Assessment>> findAssessmentsByBatch(@PathVariable("id") Integer id){
        log.debug("Inside findAssessmentsByBatch");
        List<Assessment> temp = as.findAssessmentsByBatchId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/assessment/category/{id}")
    public ResponseEntity<List<Assessment>> findAssessmentsByCategory(@PathVariable("id") Integer id){
        log.debug("Inside findAssessmentsByCategory");
        List<Assessment> temp = as.findAssessmentsByCategory(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/assessment/{id}")
    public ResponseEntity<Assessment> findAssessmentById(@PathVariable("id") Integer id){
        log.debug("Inside findAssessmentById");
        Assessment temp = as.findAssessmentById(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @PostMapping(value="/all/assessment/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) {
    	Assessment temp = as.createAssessment(assessment);
    	if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
    	return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }
  
    @PutMapping(value="all/assessment/update", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Assessment> updateAssessment(@Valid @RequestBody Assessment assessment) {
      log.debug("Updating Assessment: " + assessment);
      Assessment temp = as.updateAssessment(assessment);
      if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @DeleteMapping(value="all/assessment/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Boolean> deleteLocation(@Valid @RequestBody Assessment assessment) {
      Boolean temp = as.deleteAssessment(assessment);
      if(!temp) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
}