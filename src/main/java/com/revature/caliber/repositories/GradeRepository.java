package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
	
	public List<Grade> findGradesByTraineeId(Integer id);
	public List<Grade> findGradesByAssessmentId(Integer id);
	public List<Grade> findGradesByAssessmentIdIn(List<Integer> id);
}
