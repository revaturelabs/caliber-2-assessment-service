package com.revature.caliber.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="NOTE")
public class Note {
	
	@Id
	@Column(name="NOTE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name="NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private Integer noteId;
	
	@Length(min=0, max=4000)
	@Column(name="NOTE_CONTENT")
	private String noteContent;
	
	@Column(name="NOTE_TYPE")
	private String noteType;
	
	@Min(value=1)
	@Column(name="WEEK_NUMBER")
	private Integer weekNumber;
	
	@Column(name="BATCH_ID")
	private Integer batchId;
	
	@Column(name="TRAINEE_ID")
	private Integer traineeId;

	public Note() {
		super();
	}

	public Note(Integer noteId, String noteContent, String noteType, Integer weekNumber, Integer batchId,
			Integer traineeId) {
		super();
		this.noteId = noteId;
		this.noteContent = noteContent;
		this.noteType = noteType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.traineeId = traineeId;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
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

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((noteContent == null) ? 0 : noteContent.hashCode());
		result = prime * result + ((noteId == null) ? 0 : noteId.hashCode());
		result = prime * result + ((noteType == null) ? 0 : noteType.hashCode());
		result = prime * result + ((traineeId == null) ? 0 : traineeId.hashCode());
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
		Note other = (Note) obj;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId)) return false;
		if (noteContent == null) {
			if (other.noteContent != null)
				return false;
		} else if (!noteContent.equals(other.noteContent)) return false;
		if (noteId == null) {
			if (other.noteId != null)
				return false;
		} else if (!noteId.equals(other.noteId)) return false;
		if (noteType == null) {
			if (other.noteType != null)
				return false;
		} else if (!noteType.equals(other.noteType)) return false;
		if (traineeId == null) {
			if (other.traineeId != null)
				return false;
		} else if (!traineeId.equals(other.traineeId)) return false;
		if (weekNumber == null) {
			if (other.weekNumber != null)
				return false;
		} else if (!weekNumber.equals(other.weekNumber)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", noteContent=" + noteContent + ", noteType=" + noteType + ", weekNumber="
				+ weekNumber + ", batchId=" + batchId + ", traineeId=" + traineeId + "]";
	}
	
	
	

}
