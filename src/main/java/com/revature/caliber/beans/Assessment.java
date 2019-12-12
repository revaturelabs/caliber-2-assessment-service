package com.revature.caliber.beans;

import com.revature.caliber.dto.AssessmentDTO;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="ASSESSMENT")
//@Document(collection = "assessment")
public class Assessment {
	
	@Id
	@Column(name="ASSESSMENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ASSESSMENT_ID_SEQUENCE")
	@SequenceGenerator(name = "ASSESSMENT_ID_SEQUENCE", sequenceName = "ASSESSMENT_ID_SEQUENCE")
	private int assessmentId;
	
	@NotNull
	@Min(value = 1)
	@Column(name="RAW_SCORE")
	private int rawScore;
	
	@Column(name="ASSESSMENT_TITLE")
	private String assessmentTitle;
	
	@NotNull
	@Column(name="ASSESSMENT_TYPE")
	private String assessmentType;
	
	@NotNull
	@Min(value = 1)
	@Column(name="WEEK_NUMBER")
	private int weekNumber;
	
	@NotNull
	@Column(name="BATCH_ID")
	private int batchId;
	
	@NotNull
	@Column(name="ASSESSMENT_CATEGORY")
	private int assessmentCategory;

	public Assessment() {
		super();
		this.rawScore = 1;
		this.assessmentType = "None";
		this.weekNumber = -1;
		this.batchId = -1;
		this.assessmentCategory = -1;
	}

	public Assessment(int assessmentId, int rawScore, String assessmentTitle, String assessmentType,
			int weekNumber, int batchId, int assessmentCategory) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.assessmentTitle = assessmentTitle;
		this.assessmentType = assessmentType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.assessmentCategory = assessmentCategory;
	}

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getRawScore() {
		return rawScore;
	}

	public void setRawScore(int rawScore) {
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

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getAssessmentCategory() {
		return assessmentCategory;
	}

	public void setAssessmentCategory(int assessmentCategory) {
		this.assessmentCategory = assessmentCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Assessment that = (Assessment) o;
		return assessmentId == that.assessmentId &&
						rawScore == that.rawScore &&
						weekNumber == that.weekNumber &&
						batchId == that.batchId &&
						assessmentCategory == that.assessmentCategory &&
						Objects.equals(assessmentTitle, that.assessmentTitle) &&
						Objects.equals(assessmentType, that.assessmentType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assessmentId, rawScore, assessmentTitle, assessmentType, weekNumber, batchId, assessmentCategory);
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", rawScore=" + rawScore + ", assessmentTitle="
				+ assessmentTitle + ", assessmentType=" + assessmentType + ", weekNumber=" + weekNumber + ", batchId="
				+ batchId + ", assessmentCategory=" + assessmentCategory + "]";
	}

	public static Assessment fromDto(AssessmentDTO dto) {
		if (dto != null) {
			return new Assessment(dto.getAssessmentId(), dto.getRawScore(), dto.getAssessmentTitle(), dto.getAssessmentType(), dto.getWeekNumber(), dto.getBatchId(), dto.getAssessmentCategory());
		}
		return null;
	}
	

}
