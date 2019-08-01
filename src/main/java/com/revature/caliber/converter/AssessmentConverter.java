package com.revature.caliber.converter;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.dto.AssessmentDTO;

public class AssessmentConverter {
	
	private AssessmentConverter() {
		
	}

	public static AssessmentDTO convert(Assessment a) {
		
		AssessmentDTO assessment = new AssessmentDTO();
		assessment.setAssessmentCategory(a.getAssessmentCategory());
		assessment.setAssessmentId(a.getAssessmentId());
		assessment.setAssessmentTitle(a.getAssessmentTitle());
		assessment.setAssessmentType(a.getAssessmentType());
		assessment.setBatchId(a.getBatchId());
		assessment.setRawScore(a.getRawScore());
		assessment.setWeekNumber(a.getWeekNumber());
		
		return assessment;
		
	}
	
public static Assessment convert(AssessmentDTO a) {
		
		Assessment assessment = new Assessment();
		assessment.setAssessmentCategory(a.getAssessmentCategory());
		assessment.setAssessmentId(a.getAssessmentId());
		assessment.setAssessmentTitle(a.getAssessmentTitle());
		assessment.setAssessmentType(a.getAssessmentType());
		assessment.setBatchId(a.getBatchId());
		assessment.setRawScore(a.getRawScore());
		assessment.setWeekNumber(a.getWeekNumber());
		
		return assessment;
		
	}
}
