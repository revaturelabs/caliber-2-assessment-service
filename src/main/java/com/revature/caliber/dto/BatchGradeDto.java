package com.revature.caliber.dto;

/**
 * @author William Gentry
 */
public class BatchGradeDto {

	private String traineeName;

	private double average;

	public BatchGradeDto(String traineeName, double average) {
		this.traineeName = traineeName;
		this.average = average;
	}

	public String getTraineeName() {
		return traineeName;
	}

	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}
}
