package com.revature.caliber.dto;

public class AssessmentDTO {

	private Integer assessmentId;
	
	private Integer rawScore;
	
	private String assessmentTitle;
	
	private String assessmentType;
	
	private Integer weekNumber;
	
	private Integer batchId;
	
	private Integer assessmentCategory;

	public AssessmentDTO() {
		super();
	}

	public AssessmentDTO(Integer assessmentId, Integer rawScore, String assessmentTitle, String assessmentType,
			Integer weekNumber, Integer batchId, Integer assessmentCategory) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.assessmentTitle = assessmentTitle;
		this.assessmentType = assessmentType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.assessmentCategory = assessmentCategory;
	}

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Integer getRawScore() {
		return rawScore;
	}

	public void setRawScore(Integer rawScore) {
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

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getAssessmentCategory() {
		return assessmentCategory;
	}

	public void setAssessmentCategory(Integer assessmentCategory) {
		this.assessmentCategory = assessmentCategory;
	}

	
}
