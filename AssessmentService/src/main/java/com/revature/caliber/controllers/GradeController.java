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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.services.GradeService;

@RestController
@CrossOrigin("*")
public class GradeController {
    
    private Logger log = Logger.getLogger("GradeController.class");
    
    @Autowired
    private GradeService gs;
    
    @GetMapping("/all/grade/all")
    public ResponseEntity<List<Grade>> findAllGrades(){
        log.debug("Inside getAllGrades");
        List<Grade> temp =  gs.findAllGrades();
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/trainee/{id}")
    public ResponseEntity<List<Grade>> findGradesByTrainee(@PathVariable("id") Integer id){
        log.debug("Inside findGradesByTrainee");
        List<Grade> temp =  gs.findGradesByTraineeId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/assessment/{id}")
    public ResponseEntity<List<Grade>> findGradesByAssessment(@PathVariable("id") Integer id){
        log.debug("Inside findGradesByAssessment");
        List<Grade> temp =  gs.findGradesByAssessmentId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    

    
    @GetMapping("/all/grade/{id}")
    public ResponseEntity<Grade> findGradeById(@PathVariable("id") Integer id){
        log.debug("Inside findGradeById");
        Grade temp = gs.findGradeById(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/batch/{id}/week/{num}")
    public ResponseEntity<List<Grade>> findGradeByWeekNum(@PathVariable("id")Integer id, @PathVariable("num")Integer num){
    	log.debug("Inside findGradeByWeekNum");
    	List<Grade> temp = gs.findGradesByBatchIdAndWeekNum(id, num);
    	if(temp == null) 
    		return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
    	
    	return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @PostMapping(value="/all/grade/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
    	gs.createGrade(grade);
    	return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }
  
    @PutMapping(value="all/grade/update", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody Grade grade) {
      log.debug("Updating Grade: " + grade);
      Grade temp = gs.updateGrade(grade);
      if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @DeleteMapping(value="all/grade/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Boolean> deleteLocation(@Valid @RequestBody Grade grade) {
      Boolean temp = gs.deleteGrade(grade);
      if(!temp) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/average")
    public ResponseEntity<Float> findAverageGradeByWeek(@RequestParam(name="assessment", required=false) Integer assessmentId, @RequestParam(name="batch", required=false) Integer batchId, @RequestParam(name="week", required=false) Integer weekNum){
    	if(assessmentId != null && batchId == null && weekNum == null) {
            log.debug("Inside findAverageGradeByAssessment");
            Float temp =  gs.findAverageAssessment(assessmentId);
            if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        } else if (assessmentId == null && batchId != null && weekNum != null) {
        	log.debug("Inside findAverageGradeByWeek");
            Float temp =  gs.findAvgAssessments(batchId, weekNum);
            if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        } else  {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	
    }
    

    
}