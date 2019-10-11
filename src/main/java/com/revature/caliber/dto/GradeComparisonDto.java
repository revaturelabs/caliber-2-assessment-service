package com.revature.caliber.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gentry
 */
public class GradeComparisonDto {

	private final Map<String, Double> traineeGrades = new HashMap<>();
	private final Map<String, Double> restOfBatchGrades = new HashMap<>();

	public void addTraineeGrade(String assessmentType, double average) {
		this.traineeGrades.putIfAbsent(assessmentType, average);
	}

	public void addRestOfBatchGrades(String assessmentType, double average) {
		this.restOfBatchGrades.putIfAbsent(assessmentType, average);
	}

	public Map<String, Double> getTraineeGrades() {
		return traineeGrades;
	}

	public Map<String, Double> getRestOfBatchGrades() {
		return restOfBatchGrades;
	}
}
