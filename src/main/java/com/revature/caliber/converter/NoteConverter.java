package com.revature.caliber.converter;

import com.revature.caliber.beans.Note;
import com.revature.caliber.dto.NoteDTO;

public class NoteConverter {
	
	private NoteConverter() {}

	public static NoteDTO convert(Note note) {
		
		NoteDTO n = new NoteDTO();
		n.setNoteId(note.getNoteId());
		n.setNoteContent(note.getNoteContent());
		n.setNoteType(note.getNoteType());
		n.setWeekNumber(note.getWeekNumber());
		n.setBatchId(note.getBatchId());
		n.setTraineeId(note.getTraineeId());
		
		return n;
	}
	
	public static Note convert(NoteDTO note) {
		
		Note n = new Note();
		n.setNoteId(note.getNoteId());
		n.setNoteContent(note.getNoteContent());
		n.setNoteType(note.getNoteType());
		n.setWeekNumber(note.getWeekNumber());
		n.setBatchId(note.getBatchId());
		n.setTraineeId(note.getTraineeId());
		
		return n;
	}
}
