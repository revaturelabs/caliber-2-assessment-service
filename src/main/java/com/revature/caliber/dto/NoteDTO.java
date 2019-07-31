package com.revature.caliber.dto;

public class NoteDTO {

	private Integer noteId;
	private String noteContent;
	private String noteType;
	private Integer weekNumber;
	private Integer batchId;
	private Integer traineeId;
	
	public NoteDTO() {
		super();
	}

	public NoteDTO(Integer noteId, String noteContent, String noteType, Integer weekNumber, Integer batchId,
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
	public String toString() {
		return "NoteDTO [noteId=" + noteId + ", noteContent=" + noteContent + ", noteType=" + noteType + ", weekNumber="
				+ weekNumber + ", batchId=" + batchId + ", traineeId=" + traineeId + "]";
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

}
