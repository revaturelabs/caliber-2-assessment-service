package com.revature.caliber.beans;

import java.util.Date;
import java.util.Objects;

public class BatchEntity {
	private int batchId;
	
	private String trainingName;
	
	private String trainingType;
	
	private String skillType;
	
	private String trainer;
	
	private String coTrainer;
	
	private int locationId;
	
	private String location;
	
	private Date startDate;
	
	private Date endDate;
	
	private int goodGrade;
	
	private int passingGrade;
	
	private int weeks;
	
	public BatchEntity(String trainingName, String trainingType, String skillType, String trainer,
			String coTrainer, int locationId, Date startDate, Date endDate, int goodGrade,
			int passingGrade, int weeks) {
		super();
		this.trainingName = trainingName;
		this.trainingType = trainingType;
		this.skillType = skillType;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.locationId = locationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
		this.weeks = weeks;
	}
	
	/**
	 * Default constructor of BatchEntity.
	 */
	public BatchEntity() {
		super();
	}
	
	/**
	 * Getter for training name.
	 * @return A String that holds the batch's training name.
	 */
	public String getTrainingName() {
		return trainingName;
	}
	/**
	 * Setter for training name.
	 * @param trainingName A String that holds the batch's training name.
	 */
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	/**
	 * Getter for training type.
	 * @return A String that holds the batch's training type.
	 */
	public String getTrainingType() {
		return trainingType;
	}
	/**
	 * Setter for training type.
	 * @param trainingType A String that holds the batch's training type.
	 */
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	/**
	 * Getter for skill type.
	 * @return A String that holds the batch's skill type.
	 */
	public String getSkillType() {
		return skillType;
	}
	/**
	 * Setter for skill type.
	 * @param skillType A String that holds the batch's skill type.
	 */
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	/**
	 * Getter for trainer.
	 * @return A String that holds the batch's trainer.
	 */
	public String getTrainer() {
		return trainer;
	}
	/**
	 * Setter for trainer.
	 * @param trainer A String that holds the batch's trainer.
	 */
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	/**
	 * Getter for co-trainer.
	 * @return A String that holds the batch's co-trainer.
	 */
	public String getCoTrainer() {
		return coTrainer;
	}
	/**
	 * Setter for co-trainer.
	 * @param coTrainer A String that holds the batch's co-trainer.
	 */
	public void setCoTrainer(String coTrainer) {
		this.coTrainer = coTrainer;
	}

	/**
	 * Getter for start date.
	 * @return A Date that holds the batch's start date.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Setter for start date.
	 * @param startDate A Date that holds the batch's start date.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Getter for end date.
	 * @return A Date that holds the batch's end date.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Setter for end date.
	 * @param endDate A Date that holds the batch's end date.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Getter for good grade.
	 * @return A Date that holds the batch's good grade.
	 */
	public int getGoodGrade() {
		return goodGrade;
	}
	/**
	 * Setter for good date.
	 * @param goodGrade A int that holds the batch's good grade.
	 */
	public void setGoodGrade(int goodGrade) {
		this.goodGrade = goodGrade;
	}
	/**
	 * Getter for passing grade.
	 * @return A int that holds the batch's passing grade.
	 */
	public int getPassingGrade() {
		return passingGrade;
	}
	/**
	 * Setter for passing grade.
	 * @param passingGrade A int that holds the batch's passing grade.
	 */
	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}
	/**
	 * Getter for batch id.
	 * @return A int that holds the batch's id.
	 */
	public int getBatchId() {
		return batchId;
	}
	/**
	 * Setter for batch id.
	 * @param batchId A int that holds the batch's id.
	 */
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	
	/**
	 * Return a String to represent the BatchEntity.
	 * @return A String to represent the BatchEntity.
	 */
	@Override
	public String toString() {
		return "BatchEntity [batchId=" + batchId + ", trainingName=" + trainingName + ", trainingType=" + trainingType
				+ ", skillType=" + skillType + ", trainer=" + trainer + ", coTrainer=" + coTrainer + ", locationId=" + locationId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", goodGrade=" + goodGrade + ", passingGrade=" + passingGrade + ", weeks=" + weeks + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BatchEntity that = (BatchEntity) o;
		return batchId == that.batchId &&
						locationId == that.locationId &&
						goodGrade == that.goodGrade &&
						passingGrade == that.passingGrade &&
						weeks == that.weeks &&
						Objects.equals(trainingName, that.trainingName) &&
						Objects.equals(trainingType, that.trainingType) &&
						Objects.equals(skillType, that.skillType) &&
						Objects.equals(trainer, that.trainer) &&
						Objects.equals(coTrainer, that.coTrainer) &&
						Objects.equals(location, that.location) &&
						Objects.equals(startDate, that.startDate) &&
						Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(batchId, trainingName, trainingType, skillType, trainer, coTrainer, locationId, location, startDate, endDate, goodGrade, passingGrade, weeks);
	}
}
