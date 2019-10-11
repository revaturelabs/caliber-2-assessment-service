package com.revature.caliber.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class SpiderGraphDto {

	private int traineeId;
	private String assessmentType;
	private double score;
	private int week;

	public SpiderGraphDto() {
	}

	public SpiderGraphDto(int traineeId, String assessmentType, double score, int week) {
		this.traineeId = traineeId;
		this.assessmentType = assessmentType;
		this.score = score;
		this.week = week;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SpiderGraphDto that = (SpiderGraphDto) o;
		return traineeId == that.traineeId &&
						Double.compare(that.score, score) == 0 &&
						week == that.week &&
						Objects.equals(assessmentType, that.assessmentType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(traineeId, assessmentType, score, week);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", SpiderGraphDto.class.getSimpleName() + "[", "]")
						.add("traineeId=" + traineeId)
						.add("assessmentType='" + assessmentType + "'")
						.add("score=" + score)
						.add("week=" + week)
						.toString();
	}
}
