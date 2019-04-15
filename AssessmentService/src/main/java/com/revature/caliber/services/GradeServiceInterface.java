package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;

@Service
public interface GradeServiceInterface {
	public List<Grade> findAllGrades();
	public Grade findGradeById(Integer id);
	public Grade createGrade(Grade g);
	public Grade updateGrade(Grade g);
	public Boolean deleteGrade(Grade g);
	public List<Grade> findGradesByTraineeId(Integer id);
	public List<Grade> findGradesByAssessmentId(Integer id);
	public Float findAvgAssessments(Integer id, Integer weekNum);
}
