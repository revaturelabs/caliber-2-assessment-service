package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;

@Service
public interface GradeServiceInterface {
	public List<Grade> findAllGrades();
	public Grade findGradeById(Integer id);

}
