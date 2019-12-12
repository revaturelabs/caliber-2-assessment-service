package com.revature.caliber.dto;

import com.revature.caliber.beans.Assessment;

public class AssessmentDTO {

	private int assessmentId;

	private int rawScore;

	private String assessmentTitle;

	private String assessmentType;

	private int weekNumber;

	private int batchId;

	private int assessmentCategory;

	public AssessmentDTO() {
		super();
	}

	public AssessmentDTO(int assessmentId, int rawScore, String assessmentTitle, String assessmentType,
			int weekNumber, int batchId, int assessmentCategory) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.assessmentTitle = assessmentTitle;
		this.assessmentType = assessmentType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.assessmentCategory = assessmentCategory;
	}

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getRawScore() {
		return rawScore;
	}

	public void setRawScore(int rawScore) {
		this.rawScore = rawScore;
	}

	public String getAssessmentTitle() {
		return assessmentTitle;
	}

	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getAssessmentCategory() {
		return assessmentCategory;
	}

	public void setAssessmentCategory(int assessmentCategory) {
		this.assessmentCategory = assessmentCategory;
	}

	public static AssessmentDTO fromAssessment(Assessment assessment) {
		if (assessment != null) {
			return new AssessmentDTO(assessment.getAssessmentId(), assessment.getRawScore(), assessment.getAssessmentTitle(), assessment.getAssessmentType(), assessment.getWeekNumber(), assessment.getBatchId(), assessment.getAssessmentCategory());
		}
		return new AssessmentDTO();
	}

}
