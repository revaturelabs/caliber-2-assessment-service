package com.revature.caliber.beans;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class GradeTest {

	@Test
	public void testGrade() {

		Date d = new Date();

		Grade g = new Grade();
		g.setAssessmentId(5);
		g.setDateReceived(d);
		g.setGradeId(80);
		g.setScore(60);
		g.setTraineeId(6);

		assertEquals(Integer.valueOf(5), g.getAssessmentId());
		assertEquals(Integer.valueOf(80), g.getGradeId());
		assertEquals(Float.valueOf(60), g.getScore(), .05);
		assertEquals(Integer.valueOf(6), g.getTraineeId());
		assertEquals(d, g.getDateReceived());
	}

	@Test
	public void testGradeConstructor() {

		Date d = new Date();
		Grade g = new Grade(1, d, 50, 5, 4);

		assertEquals(Integer.valueOf(5), g.getAssessmentId());
		assertEquals(d, g.getDateReceived());
		assertEquals(Float.valueOf(50), g.getScore(), .05);
		assertEquals(Integer.valueOf(1), g.getGradeId());

		assertEquals(Integer.valueOf(4), g.getTraineeId());

	}
}
