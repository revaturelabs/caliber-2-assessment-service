package com.revature.caliber.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AssessmentTest {

	@Test
	public void testAssessment() {

		Assessment a = new Assessment();
		a.setAssessmentCategory(2);
		a.setAssessmentId(4);
		a.setAssessmentTitle("Java");
		a.setAssessmentType("Exam");
		a.setBatchId(5);
		a.setRawScore(80);
		a.setWeekNumber(3);

		assertEquals(2, a.getAssessmentCategory());
		assertEquals(4, a.getAssessmentId());
		assertEquals("Exam", a.getAssessmentType());
		assertEquals("Java", a.getAssessmentTitle());
		assertEquals(5, a.getBatchId());
		assertEquals(80, a.getRawScore());
		assertEquals(3, a.getWeekNumber());
	}

	@Test
	public void testAssessmentConstructor() {

		Assessment as = new Assessment(2, 60, "Spring", "Verbal", 8, 7, 8);

		assertEquals(2, as.getAssessmentId());
		assertEquals(60, as.getRawScore());
		assertEquals("Spring", as.getAssessmentTitle());
		assertEquals("Verbal", as.getAssessmentType());
		assertEquals(8, as.getWeekNumber());
		assertEquals(7, as.getBatchId());
		assertEquals(8, as.getAssessmentCategory());
	}
}
