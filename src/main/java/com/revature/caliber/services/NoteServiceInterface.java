package com.revature.caliber.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Note;
import com.revature.caliber.dto.NoteDTO;

@Service
public interface NoteServiceInterface {
	public List<Note> findAllNotes();
	public Note findNoteById(int id);
	public Note createNote(NoteDTO n);
	public Note updateNote(NoteDTO n);
	public boolean deleteNote(NoteDTO n);
	public List<Note> findNotesByTraineeId(int id);
	public List<Note> findNotesByBatchId(int id);
	public List<Note> findNotesByBatchIdAndWeekNumber(int bid, int weekNum);
	public Map<Integer, List<Note>> findNotesByBatchAndWeek(int batchId, int week);
	Optional<Note> findBatchNoteByBatchAndWeek(int batchId, int week);
	Note upsertNote(NoteDTO noteDTO);
}
