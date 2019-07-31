package com.revature.caliber.converter;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.dto.GradeDTO;

public class GradeConverter {

	public static GradeDTO convert(Grade grade) {
		
		GradeDTO g = new GradeDTO();
		g.setGradeId(grade.getGradeId());
		g.setDateReceived(grade.getDateReceived());
		g.setScore(grade.getScore());
		g.setAssessmentId(grade.getAssessmentId());
		g.setTraineeId(grade.getTraineeId());
		
		return g;
	}
	
	public static Grade convert(GradeDTO grade) {
		
		Grade g = new Grade();
		g.setGradeId(grade.getGradeId());
		g.setDateReceived(grade.getDateReceived());
		g.setScore(grade.getScore());
		g.setAssessmentId(grade.getAssessmentId());
		g.setTraineeId(grade.getTraineeId());
		
		return g;
	}
}
