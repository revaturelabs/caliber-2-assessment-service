package com.revature.caliber.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name="NOTE")
public class Note {
	
	@Id
	@Column(name="NOTE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name="NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private int noteId;
	
	@Length(min=0, max=4000)
	@Column(name="NOTE_CONTENT")
	private String noteContent;
	
	@Column(name="NOTE_TYPE")
	private String noteType;
	
	@Min(value=1)
	@Column(name="WEEK_NUMBER")
	private int weekNumber;
	
	@Column(name="BATCH_ID")
	private int batchId;
	
	@Column(name="TRAINEE_ID")
	private int traineeId;

	public Note() {
		super();
	}

	public Note(int noteId, String noteContent, String noteType, int weekNumber, int batchId,
			int traineeId) {
		super();
		this.noteId = noteId;
		this.noteContent = noteContent;
		this.noteType = noteType;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.traineeId = traineeId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
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

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return noteId == note.noteId &&
						weekNumber == note.weekNumber &&
						batchId == note.batchId &&
						traineeId == note.traineeId &&
						Objects.equals(noteContent, note.noteContent) &&
						Objects.equals(noteType, note.noteType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(noteId, noteContent, noteType, weekNumber, batchId, traineeId);
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", noteContent=" + noteContent + ", noteType=" + noteType + ", weekNumber="
				+ weekNumber + ", batchId=" + batchId + ", traineeId=" + traineeId + "]";
	}
	
	
	

}
