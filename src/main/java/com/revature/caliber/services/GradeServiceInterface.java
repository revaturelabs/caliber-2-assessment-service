package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.dto.GradeDTO;

@Service
public interface GradeServiceInterface {
	public List<Grade> findAllGrades();
	public Grade findGradeById(Integer id);
	public Grade createGrade(GradeDTO g);
	public Grade updateGrade(GradeDTO g);
	public Boolean deleteGrade(GradeDTO g);
	public List<Grade> findGradesByTraineeId(Integer id);
	public List<Grade> findGradesByBatchId(Integer id);
	public List<Grade> findGradesByAssessmentId(Integer id);
	public List<Grade> findGradesByBatchIdAndWeekNumber(Integer id, Integer weekNumber);
	public Float findAvgAssessments(Integer id, Integer weekNum);
	public Float findAverageAssessment(Integer id);

}
