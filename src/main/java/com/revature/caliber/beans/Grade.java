package com.revature.caliber.beans;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="GRADE")
public class Grade {

	@Id
	@Column(name="GRADE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "GRADE_ID_SEQUENCE")
	@SequenceGenerator(name = "GRADE_ID_SEQUENCE", sequenceName = "GRADE_ID_SEQUENCE")
	private Integer gradeId;
	
	@NotNull
	@Column(name="DATE_RECEIVED")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateReceived;
	
	@NotNull
	@Min(value=0)
	@Column(name="SCORE")
	private float score;
	
	@NotNull
	@Column(name="ASSESSMENT_ID")
	private Integer assessmentId;
	
	@NotNull
	@Column(name="TRAINEE_ID")
	private Integer traineeId;

	public Grade() {
		super();
		this.dateReceived = new Date(0L);
		this.score = 0;
		this.assessmentId = -1;
		this.traineeId = -1;
	}

	public Grade(Integer gradeId, Date dateReceived, float score, Integer assessmentId, Integer traineeId) {
		super();
		this.gradeId = gradeId;
		this.dateReceived = dateReceived;
		this.score = score;
		this.assessmentId = assessmentId;
		this.traineeId = traineeId;
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
		Grade other = (Grade) obj;
		if (assessmentId == null) {
			if (other.assessmentId != null)
				return false;
		} else if (!assessmentId.equals(other.assessmentId)) return false;
		if (dateReceived == null) {
			if (other.dateReceived != null)
				return false;
		} else if (!dateReceived.equals(other.dateReceived)) return false;
		if (gradeId == null) {
			if (other.gradeId != null)
				return false;
		} else if (!gradeId.equals(other.gradeId)) return false;
		if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
			return false;
		if (traineeId == null) {
			if (other.traineeId != null)
				return false;
		} else if (!traineeId.equals(other.traineeId)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", dateReceived=" + dateReceived + ", score=" + score + ", assessmentId="
				+ assessmentId + ", traineeId=" + traineeId + "]";
	}
	
	
	
	
}
