package com.revature.caliber.beans;

import java.io.Serializable;

public class Trainee implements Serializable {

	private static final long serialVersionUID = -9090223980655307018L;

	private int traineeId;
	
	private String resourceId;

	private String name;

	private String email;

	private TrainingStatus trainingStatus;

	private int batchId;

	private String phoneNumber;

	private String skypeId;

	private String profileUrl;

	private String recruiterName;

	private String college;

	private String degree;

	private String major;

	private String techScreenerName;

	private Double techScreenScore;

	private String projectCompletion;

	private TraineeFlag flagStatus;

	private String flagNotes;

	public Trainee() {
		super();
		this.flagStatus = TraineeFlag.NONE;
	}

	/**
	 * Constructor used mostly for testing. Default TrainingStatus as Training
	 * 
	 * @param name       The name of the trainee
	 * @param resourceId The resource id
	 * @param email      The trainee's email address
	 * @param batchId    The id of the trainee's batch
	 */
	public Trainee(String name, String resourceId, String email, int batchId) {
		super();
		this.name = name;
		this.resourceId = resourceId;
		this.email = email;
		this.trainingStatus = TrainingStatus.TRAINING;
		this.batchId = batchId;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TrainingStatus getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(TrainingStatus trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((profileUrl == null) ? 0 : profileUrl.hashCode());
		result = prime * result + ((skypeId == null) ? 0 : skypeId.hashCode());
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", resourceId=" + resourceId + ", name=" + name + ", email=" + email
				+ ", trainingStatus=" + trainingStatus + ", batchId=" + batchId + ", phoneNumber=" + phoneNumber
				+ ", skypeId=" + skypeId + ", profileUrl=" + profileUrl + ", recruiterName=" + recruiterName
				+ ", college=" + college + ", degree=" + degree + ", major=" + major + ", techScreenerName="
				+ techScreenerName + ", techScreenScore=" + techScreenScore + ", projectCompletion=" + projectCompletion
				+ ", flagStatus=" + flagStatus + ", flagNotes=" + flagNotes + "]";
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTechScreenerName() {
		return techScreenerName;
	}

	public void setTechScreenerName(String techScreenerName) {
		this.techScreenerName = techScreenerName;
	}

	public String getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(String projectCompletion) {
		this.projectCompletion = projectCompletion;
	}

	public TraineeFlag getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(TraineeFlag flagStatus) {
		this.flagStatus = flagStatus;
	}

	public String getFlagNotes() {
		return flagNotes;
	}

	public void setFlagNotes(String flagNotes) {
		this.flagNotes = flagNotes;
	}

	public Double getTechScreenScore() {
		return techScreenScore;
	}

	public void setTechScreenScore(Double techScreenScore) {
		this.techScreenScore = techScreenScore;
	}
}
