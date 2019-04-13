package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/all/grade/{id}")
    public ResponseEntity<Grade> findGradeById(@PathVariable("id") Integer id){
        log.debug("Inside findGradeById");
        Grade temp = gs.findGradeById(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
}