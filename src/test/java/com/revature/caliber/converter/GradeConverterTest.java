package com.revature.caliber.converter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.dto.GradeDTO;

public class GradeConverterTest {

	@Test
	public void testCovertToDTO() {

		Date d = new Date();

		Grade g = new Grade();
		g.setAssessmentId(5);
		g.setDateReceived(d);
		g.setGradeId(80);
		g.setScore(60);
		g.setTraineeId(6);

		GradeConverter.convert(g);

		assertEquals(5, g.getAssessmentId());
		assertEquals(80, g.getGradeId());
		assertEquals(Float.valueOf(60), g.getScore(), .05);
		assertEquals(6, g.getTraineeId());
		assertEquals(d, g.getDateReceived());
	}

	@Test
	public void testConvertFromDTO() {

		Date d = new Date();
		GradeDTO g = new GradeDTO(1, d, 50, 5, 4);

		GradeConverter.convert(g);

//		assertEquals(Integer.valueOf(5), g.getAssessmentId());
//		assertEquals(d, g.getDateReceived());
//		assertEquals(Float.valueOf(50), g.getScore(), .05);
//		assertEquals(Integer.valueOf(1), g.getGradeId());

//		assertEquals(Integer.valueOf(4), g.getTraineeId());

	}
}
