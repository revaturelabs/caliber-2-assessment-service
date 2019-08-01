package com.revature.caliber.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Note;
import com.revature.caliber.repositories.NoteRepository;

public class NoteServiceTests {

	NoteRepository noteRepository = mock(NoteRepository.class);
	List<Note> noteList1 = new ArrayList<>();
	
	@Before
	public void setup() {
		Note note1 = new Note(1, "testContent1", "testType", 1, 1, 1);
		
		noteList1.add(note1);
		
		when(noteRepository.findNotesByBatchId(1)).thenReturn(noteList1);
		when(noteRepository.findNotesByBatchId(4)).thenReturn(null);
		when(noteRepository.findNotesByTraineeId(1)).thenReturn(noteList1);
		when(noteRepository.findNotesByTraineeId(4)).thenReturn(null);
		when(noteRepository.findNotesByBatchIdAndWeekNumber(1,  1)).thenReturn(noteList1);
		when(noteRepository.findNotesByBatchIdAndWeekNumber(1,  2)).thenReturn(null);
	}
	
	@Test
	public void testFindNotesByBatchId() {
		assertEquals(noteList1, noteRepository.findNotesByBatchId(1));
	}
	
	@Test
	public void testFindNotesByBatchIdNonExistant() {
		assertEquals(null, noteRepository.findNotesByBatchId(4));
	}
	
	@Test
	public void testFindNotesByTraineeId() {
		assertEquals(noteList1, noteRepository.findNotesByTraineeId(1));
	}
	
	@Test
	public void testFindNotesByTraineeIdNonExistant() {
		assertEquals(null, noteRepository.findNotesByTraineeId(4));
	}
	
	@Test
	public void testFindNotesByBatchIdAndWeekNumber() {
		assertEquals(noteList1, noteRepository.findNotesByBatchIdAndWeekNumber(1, 1));
	}
	
	@Test
	public void testFindNotesByBatchIdAndWeekNumberBadInput() {
		assertEquals(null, noteRepository.findNotesByBatchIdAndWeekNumber(1, 2));
	}
}
