package com.revature.caliber.beans;

import java.util.List;

/**
 * 
 * @author Jace Dembski
 * Used to arrange necessary information to send in response for a request for missing grades.
 */
public class MissingGrade {

	private Integer batchId;
	
	private String trainer;
	
	private String location;
	
	private List<Integer> missingWeeks;

	public MissingGrade(Integer batchId, String trainer, String location, List<Integer> missingWeeks) {
		super();
		this.batchId = batchId;
		this.trainer = trainer;
		this.location = location;
		this.missingWeeks = missingWeeks;
	}
	
	

	public MissingGrade() {
		super();
	}



	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Integer> getMissingWeeks() {
		return missingWeeks;
	}

	public void setMissingWeeks(List<Integer> missingWeeks) {
		this.missingWeeks = missingWeeks;
	}
	
	
}
