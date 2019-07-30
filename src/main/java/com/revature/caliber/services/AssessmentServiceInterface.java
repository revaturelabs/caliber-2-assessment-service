package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.dto.AssessmentDTO;

@Service
public interface AssessmentServiceInterface {
	public List<Assessment> findAllAssessments();
	public Assessment findAssessmentById(Integer id);
	public Boolean deleteAssessment(AssessmentDTO as);
	public Assessment createAssessment(AssessmentDTO as);
	public Assessment updateAssessment(AssessmentDTO as);
	public List<Assessment> findAssessmentsByBatchId(Integer batchId);
	public List<Assessment> findAssessmentsByCategory(Integer categoryId);
	public List<Assessment> findAssessmentsByBatchIdAndWeekNumber(Integer id, Integer weekNumber);

}
