package com.revature.caliber.beans;

import java.util.Date;
import java.util.Objects;

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
	private int gradeId;
	
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
	private int assessmentId;
	
	@NotNull
	@Column(name="TRAINEE_ID")
	private int traineeId;

	public Grade() {
		super();
		this.dateReceived = new Date(0L);
		this.score = 0;
		this.assessmentId = -1;
		this.traineeId = -1;
	}

	public Grade(int gradeId, Date dateReceived, float score, int assessmentId, int traineeId) {
		super();
		this.gradeId = gradeId;
		this.dateReceived = dateReceived;
		this.score = score;
		this.assessmentId = assessmentId;
		this.traineeId = traineeId;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Grade grade = (Grade) o;
		return gradeId == grade.gradeId &&
						Float.compare(grade.score, score) == 0 &&
						assessmentId == grade.assessmentId &&
						traineeId == grade.traineeId &&
						Objects.equals(dateReceived, grade.dateReceived);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gradeId, dateReceived, score, assessmentId, traineeId);
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", dateReceived=" + dateReceived + ", score=" + score + ", assessmentId="
				+ assessmentId + ", traineeId=" + traineeId + "]";
	}
	
	
	
	
}
