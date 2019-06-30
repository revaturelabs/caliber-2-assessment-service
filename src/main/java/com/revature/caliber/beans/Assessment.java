package com.revature.caliber.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ASSESSMENT")
//@Document(collection = "assessment")
public class Assessment {
	
	@Id
	@Column(name="ASSESSMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer assessmentId;
	
	@NotNull
	@Min(value = 1)
	@Column(name="RAW_SCORE")
	private Integer rawScore;
	
	@Column(name="ASSESSMENT_TITLE")
	private String assessmentTitle;
	
	@NotNull
	@Column(name="ASSESSMENT_TYPE")
	private String assessmentType;
	
	@NotNull
	@Min(value = 1)
	@Column(name="WEEK_NUMBER")
	private Integer weekNumber;
	
	@NotNull
	@Column(name="BATCH_ID")
	private Integer batchId;
	
	@NotNull
	@Column(name="ASSESSMENT_CATEGORY")
	private Integer assessmentCategory;

	public Assessment() {
		super();
		this.rawScore = 1;
		this.assessmentType = "None";
		this.weekNumber = -1;
		this.batchId = -1;
		this.assessmentCategory = -1;
	}

	public Assessment(Integer assessmentId, Integer rawScore, String assessmentTitle, String assessmentType,
			Integer weekNumber, Integer batchId, Integer assessmentCategory) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.assessmentTitle = assessmentTitle;
		this.assessmentType = assessmentType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.assessmentCategory = assessmentCategory;
	}

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Integer getRawScore() {
		return rawScore;
	}

	public void setRawScore(Integer rawScore) {
		this.rawScore = rawScore;
	}

	public String getAssessmentTitle() {
		return assessmentTitle;
	}

	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getAssessmentCategory() {
		return assessmentCategory;
	}

	public void setAssessmentCategory(Integer assessmentCategory) {
		this.assessmentCategory = assessmentCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessmentCategory == null) ? 0 : assessmentCategory.hashCode());
		result = prime * result + ((assessmentId == null) ? 0 : assessmentId.hashCode());
		result = prime * result + ((assessmentTitle == null) ? 0 : assessmentTitle.hashCode());
		result = prime * result + ((assessmentType == null) ? 0 : assessmentType.hashCode());
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((rawScore == null) ? 0 : rawScore.hashCode());
		result = prime * result + ((weekNumber == null) ? 0 : weekNumber.hashCode());
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
		Assessment other = (Assessment) obj;
		if (assessmentCategory == null) {
			if (other.assessmentCategory != null)
				return false;
		} else if (!assessmentCategory.equals(other.assessmentCategory))
			return false;
		if (assessmentId == null) {
			if (other.assessmentId != null)
				return false;
		} else if (!assessmentId.equals(other.assessmentId))
			return false;
		if (assessmentTitle == null) {
			if (other.assessmentTitle != null)
				return false;
		} else if (!assessmentTitle.equals(other.assessmentTitle))
			return false;
		if (assessmentType == null) {
			if (other.assessmentType != null)
				return false;
		} else if (!assessmentType.equals(other.assessmentType))
			return false;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (rawScore == null) {
			if (other.rawScore != null)
				return false;
		} else if (!rawScore.equals(other.rawScore))
			return false;
		if (weekNumber == null) {
			if (other.weekNumber != null)
				return false;
		} else if (!weekNumber.equals(other.weekNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", rawScore=" + rawScore + ", assessmentTitle="
				+ assessmentTitle + ", assessmentType=" + assessmentType + ", weekNumber=" + weekNumber + ", batchId="
				+ batchId + ", assessmentCategory=" + assessmentCategory + "]";
	}

	
	

}
