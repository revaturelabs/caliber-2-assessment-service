package com.revature.caliber.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoteTest {

	@Test
	public void testNote() {

		Note n = new Note();
		n.setNoteId(1);
		n.setNoteContent("Great");
		n.setNoteType("QC");
		n.setWeekNumber(4);
		n.setBatchId(100);
		n.setTraineeId(6);

		assertEquals(1, n.getNoteId());
		assertEquals("Great", n.getNoteContent());
		assertEquals("QC", n.getNoteType());
		assertEquals(2, n.getWeekNumber());
		assertEquals(100, n.getBatchId());
		assertEquals(6, n.getTraineeId());
	}

	@Test
	public void testNoteConstructor() {

		Note n = new Note(4, "Terrible", "QC", 4, 100, 6);

		assertEquals(4, n.getNoteId());
		assertEquals("Terrible", n.getNoteContent());
		assertEquals("QC", n.getNoteType());
		assertEquals(4, n.getWeekNumber());
		assertEquals(100, n.getBatchId());
		assertEquals(6, n.getTraineeId());
	}
}
