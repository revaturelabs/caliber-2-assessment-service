package com.revature.caliber.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoteTest {

	@Test
	public void testNote()  {
		
		Note n = new Note();
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
		
		Note n = new Note(4,"Terrible","QC", 4,100,6);
		
		assertEquals(Integer.valueOf(4), n.getNoteId());
		assertEquals("Terrible", n.getNoteContent());
		assertEquals("QC", n.getNoteType());
		assertEquals(Integer.valueOf(4), n.getWeekNumber());
		assertEquals(Integer.valueOf(100), n.getBatchId());
		assertEquals(Integer.valueOf(6), n.getTraineeId());
	}
}
