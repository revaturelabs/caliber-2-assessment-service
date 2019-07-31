package com.revature.caliber.dto;

import java.util.Date;

public class GradeDTO {

	private Integer gradeId;
	private Date dateReceived;
	private float score;
	private Integer assessmentId;
	private Integer traineeId;
	
	public GradeDTO(Integer gradeId, Date dateReceived, float score, Integer assessmentId, Integer traineeId) {
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

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
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

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	@Override
	public String toString() {
		return "GradeDTO [gradeId=" + gradeId + ", dateReceived=" + dateReceived + ", score=" + score
				+ ", assessmentId=" + assessmentId + ", traineeId=" + traineeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessmentId == null) ? 0 : assessmentId.hashCode());
		result = prime * result + ((dateReceived == null) ? 0 : dateReceived.hashCode());
		result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
		result = prime * result + Float.floatToIntBits(score);
		result = prime * result + ((traineeId == null) ? 0 : traineeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeDTO other = (GradeDTO) obj;
		if (assessmentId == null) {
			if (other.assessmentId != null)
				return false;
		} else if (!assessmentId.equals(other.assessmentId))
			return false;
		if (dateReceived == null) {
			if (other.dateReceived != null)
				return false;
		} else if (!dateReceived.equals(other.dateReceived))
			return false;
		if (gradeId == null) {
			if (other.gradeId != null)
				return false;
		} else if (!gradeId.equals(other.gradeId))
			return false;
		if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
			return false;
		if (traineeId == null) {
			if (other.traineeId != null)
				return false;
		} else if (!traineeId.equals(other.traineeId))
			return false;
		return true;
	}
}
