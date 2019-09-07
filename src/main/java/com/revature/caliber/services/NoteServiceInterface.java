package com.revature.caliber.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Note;
import com.revature.caliber.dto.NoteDTO;

@Service
public interface NoteServiceInterface {
	public List<Note> findAllNotes();
	public Note findNoteById(Integer id);
	public Note createNote(NoteDTO n);
	public Note updateNote(NoteDTO n);
	public Boolean deleteNote(NoteDTO n);
	public List<Note> findNotesByTraineeId(Integer id);
	public List<Note> findNotesByBatchId(Integer id);
	public List<Note> findNotesByBatchIdAndWeekNumber(Integer bid, Integer weekNum);
	public Map<Integer, List<Note>> findNotesByBatchAndWeek(Integer batchId, Integer week);
}
