package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer>{
	
	public List<Assessment> findAssessmentsByBatchId(Integer id);
	public List<Assessment> findAssessmentsByAssessmentCategory(Integer id);
	public List<Assessment> findAssessmentsByBatchIdAndWeekNumber(Integer id, Integer weekNum);
}
