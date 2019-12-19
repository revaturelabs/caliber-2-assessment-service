package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.dto.AssessmentDTO;

@Service
public interface AssessmentServiceInterface {
	public List<Assessment> findAllAssessments();
	public Assessment findAssessmentById(int id);
	public boolean deleteAssessment(AssessmentDTO as);
	public Assessment createAssessment(AssessmentDTO as);
	public Assessment updateAssessment(AssessmentDTO as);
	public List<Assessment> findAssessmentsByBatchId(int batchId);
	public List<Assessment> findAssessmentsByCategory(int categoryId);
	public List<Assessment> findAssessmentsByBatchIdAndWeekNumber(int id, int weekNumber);

}
