package com.revature.caliber.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.caliber.beans.Note;

public class NoteDTOTest {

	@Test
	public void testNote()  {
		
		NoteDTO n = new NoteDTO();
		n.setNoteId(1);
		n.setNoteContent("Great");
		n.setNoteType("QC");
		n.setWeekNumber(4);
		n.setBatchId(100);
		n.setTraineeId(6);
		
		assertEquals(Integer.valueOf(1), n.getNoteId());
		assertEquals("Great", n.getNoteContent());
		assertEquals("QC", n.getNoteType());
		assertEquals(Integer.valueOf(4), n.getWeekNumber());
		assertEquals(Integer.valueOf(100), n.getBatchId());
		assertEquals(Integer.valueOf(6), n.getTraineeId());
	}
	
	@Test
	public void testNoteConstructor()  {
		
		NoteDTO n = new NoteDTO(4,"Terrible","QC", 4,100,6);
		
		assertEquals(Integer.valueOf(4), n.getNoteId());
		assertEquals("Great", n.getNoteContent());
		assertEquals("QC", n.getNoteType());
		assertEquals(Integer.valueOf(4), n.getWeekNumber());
		assertEquals(Integer.valueOf(100), n.getBatchId());
		assertEquals(Integer.valueOf(6), n.getTraineeId());
	}
}
