package com.revature.caliber.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Note;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	public List<Note> findNotesByTraineeId(int id);
	public List<Note> findNotesByBatchId(int id);
	public List<Note> findNotesByBatchIdAndWeekNumber(int bid, int weekNum);

	@Query("SELECT n FROM Note n WHERE n.batchId = ?1 and n.weekNumber = ?2 and n.noteType = 'BATCH'")
	Optional<Note> findBatchNoteByBatchIdAndWeekNumber(int batchId, int week);
}
