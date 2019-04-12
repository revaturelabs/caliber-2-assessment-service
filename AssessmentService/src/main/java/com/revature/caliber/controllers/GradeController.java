package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Grade> findAllGrades(){
        log.debug("Inside getAllGrades");
        return gs.findAllGrades();
    }
    
    @GetMapping("/all/grade/{id}")
    public Grade findGradeById(@PathVariable("id") Integer id){
        log.debug("Inside findGradeById");
        return gs.findGradeById(id);
    }
    
}