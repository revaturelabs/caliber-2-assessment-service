package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	public List<Note> findNotesByTraineeId(Integer id);
	public List<Note> findNotesByBatchId(Integer id);
	public List<Note> findNotesByBatchIdAndWeekNumber(Integer bid, Integer weekNum);
}
