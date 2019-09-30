package com.revature.caliber.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GradeDTO {

	private int gradeId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateReceived;
	private float score;
	private int assessmentId;
	private int traineeId;

	public GradeDTO(int gradeId, Date dateReceived, float score, int assessmentId, int traineeId) {
		super();
		this.gradeId = gradeId;
		this.dateReceived = dateReceived;
		this.score = score;
		this.assessmentId = assessmentId;
		this.traineeId = traineeId;
	}

	public GradeDTO() {
		super();
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

}
