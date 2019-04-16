package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Note;

@Service
public interface NoteServiceInterface {
	public List<Note> findAllNotes();
	public Note findNoteById(Integer id);
	public Note createNote(Note n);
	public Note updateNote(Note n);
	public Boolean deleteNote(Note n);
	public List<Note> findNotesByTraineeId(Integer id);
	public List<Note> findNotesByBatchId(Integer id);
	public List<Note> findNotesByBatchIdAndWeekNumber(Integer bid, Integer weekNum);
}
