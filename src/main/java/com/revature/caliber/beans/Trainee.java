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

	private double techScreenScore;

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
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		return super.equals(obj);
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
//		result = prime * result + ((profileUrl == null) ? 0 : profileUrl.hashCode());
//		result = prime * result + ((skypeId == null) ? 0 : skypeId.hashCode());
//		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
//		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
//		return result;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((college == null) ? 0 : college.hashCode());
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((flagNotes == null) ? 0 : flagNotes.hashCode());
		result = prime * result + ((flagStatus == null) ? 0 : flagStatus.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((profileUrl == null) ? 0 : profileUrl.hashCode());
		result = prime * result + ((projectCompletion == null) ? 0 : projectCompletion.hashCode());
		result = prime * result + ((recruiterName == null) ? 0 : recruiterName.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((skypeId == null) ? 0 : skypeId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(techScreenScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((techScreenerName == null) ? 0 : techScreenerName.hashCode());
		result = prime * result + traineeId;
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
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
		Trainee other = (Trainee) obj;
		if (batchId != other.batchId)
			return false;
		if (college == null) {
			if (other.college != null)
				return false;
		} else if (!college.equals(other.college))
			return false;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (flagNotes == null) {
			if (other.flagNotes != null)
				return false;
		} else if (!flagNotes.equals(other.flagNotes))
			return false;
		if (flagStatus != other.flagStatus)
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (profileUrl == null) {
			if (other.profileUrl != null)
				return false;
		} else if (!profileUrl.equals(other.profileUrl))
			return false;
		if (projectCompletion == null) {
			if (other.projectCompletion != null)
				return false;
		} else if (!projectCompletion.equals(other.projectCompletion))
			return false;
		if (recruiterName == null) {
			if (other.recruiterName != null)
				return false;
		} else if (!recruiterName.equals(other.recruiterName))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (skypeId == null) {
			if (other.skypeId != null)
				return false;
		} else if (!skypeId.equals(other.skypeId))
			return false;
		if (Double.doubleToLongBits(techScreenScore) != Double.doubleToLongBits(other.techScreenScore))
			return false;
		if (techScreenerName == null) {
			if (other.techScreenerName != null)
				return false;
		} else if (!techScreenerName.equals(other.techScreenerName))
			return false;
		if (traineeId != other.traineeId)
			return false;
		if (trainingStatus != other.trainingStatus)
			return false;
		return true;
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

	public double getTechScreenScore() {
		return techScreenScore;
	}

	public void setTechScreenScore(double techScreenScore) {
		this.techScreenScore = techScreenScore;
	}
}
