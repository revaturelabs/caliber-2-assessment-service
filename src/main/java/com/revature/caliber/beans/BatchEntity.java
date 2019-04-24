package com.revature.caliber.beans;

import java.util.Date;

public class BatchEntity {
	private Integer batchId;
	
	private String trainingName;
	
	private String trainingType;
	
	private String skillType;
	
	private String trainer;
	
	private String coTrainer;
	
	private Integer locationId;
	
	private String location;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer goodGrade;
	
	private Integer passingGrade;
	
	private Integer weeks;
	
	public BatchEntity(String trainingName, String trainingType, String skillType, String trainer,
			String coTrainer, Integer locationId, Date startDate, Date endDate, Integer goodGrade,
			Integer passingGrade, Integer weeks) {
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
	public Integer getGoodGrade() {
		return goodGrade;
	}
	/**
	 * Setter for good date.
	 * @param goodGrade A Integer that holds the batch's good grade.
	 */
	public void setGoodGrade(Integer goodGrade) {
		this.goodGrade = goodGrade;
	}
	/**
	 * Getter for passing grade.
	 * @return A Integer that holds the batch's passing grade.
	 */
	public Integer getPassingGrade() {
		return passingGrade;
	}
	/**
	 * Setter for passing grade.
	 * @param passingGrade A Integer that holds the batch's passing grade.
	 */
	public void setPassingGrade(Integer passingGrade) {
		this.passingGrade = passingGrade;
	}
	/**
	 * Getter for batch id.
	 * @return A Integer that holds the batch's id.
	 */
	public Integer getBatchId() {
		return batchId;
	}
	/**
	 * Setter for batch id.
	 * @param batchId A Integer that holds the batch's id.
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((coTrainer == null) ? 0 : coTrainer.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((goodGrade == null) ? 0 : goodGrade.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((passingGrade == null) ? 0 : passingGrade.hashCode());
		result = prime * result + ((skillType == null) ? 0 : skillType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
		result = prime * result + ((trainingName == null) ? 0 : trainingName.hashCode());
		result = prime * result + ((trainingType == null) ? 0 : trainingType.hashCode());
		result = prime * result + ((weeks == null) ? 0 : weeks.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj.getClass() != BatchEntity.class) {
			return false;
		}
		BatchEntity other = (BatchEntity) obj;
		if(other != null && (!this.coTrainer.equals(other.getCoTrainer()) || !this.locationId.equals(other.getLocationId()) || !this.goodGrade.equals(other.getGoodGrade())
				|| !this.passingGrade.equals(other.getPassingGrade()) || !this.skillType.equals(other.getSkillType()) 
				|| !this.trainer.equals(other.getTrainer()) || !this.trainingName.equals(other.getTrainingName()) 
				|| !this.trainingType.equals(other.getTrainingType()) || this.startDate.getTime() != other.getStartDate().getTime() || this.endDate.getTime() != other.getEndDate().getTime())) {
			return false;
		}
		
		return true;
	}
	
}
