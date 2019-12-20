package com.revature.caliber.beans;

import java.util.List;

/**
 * 
 * @author Jace Dembski Used to arrange necessary information to send in
 *         response for a request for missing grades.
 */
public class MissingGrade {

	private int batchId;

	private String trainer;

	private String location;

	private List<Integer> missingWeeks;

	private String skillType;

	public MissingGrade(int batchId, String trainer, String location, List<Integer> missingWeeks, String skillType) {
		super();
		this.batchId = batchId;
		this.trainer = trainer;
		this.location = location;
		this.missingWeeks = missingWeeks;
		this.skillType = skillType;
	}

	public MissingGrade() {
		super();
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
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

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((missingWeeks == null) ? 0 : missingWeeks.hashCode());
		result = prime * result + ((skillType == null) ? 0 : skillType.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
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
		MissingGrade other = (MissingGrade) obj;
		if (batchId != other.batchId)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (missingWeeks == null) {
			if (other.missingWeeks != null)
				return false;
		} else if (!missingWeeks.equals(other.missingWeeks))
			return false;
		if (skillType == null) {
			if (other.skillType != null)
				return false;
		} else if (!skillType.equals(other.skillType))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MissingGrade [batchId=" + batchId + ", trainer=" + trainer + ", location=" + location
				+ ", missingWeeks=" + missingWeeks + ", skillType=" + skillType + "]";
	}

}
