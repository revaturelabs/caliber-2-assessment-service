package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;

@Service
public interface AssessmentServiceInterface {
	public List<Assessment> findAllAssessments();
	public Assessment findAssessmentById(Integer id);
	public Boolean deleteAssessment(Assessment as);
	public Assessment createAssessment(Assessment as);
	public Assessment updateAssessment(Assessment as);
	public List<Assessment> findAssessmentsByBatchId(Integer batchId);
	public List<Assessment> findAssessmentsByCategory(Integer categoryId);
	List<Assessment> findAssessmentsByBatchIdAndWeekNumber(Integer id, Integer weekNumber);
}
