package com.revature.caliber.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.dto.AssessmentDTO;

public class AssessmentConverterTest {

	@Test
	public void testCovertToDTO() {

		Assessment a = new Assessment();

		a.setAssessmentCategory(2);
		a.setAssessmentId(4);
		a.setAssessmentTitle("Java");
		a.setAssessmentType("Exam");
		a.setBatchId(5);
		a.setRawScore(80);
		a.setWeekNumber(3);

		AssessmentConverter.convert(a);

		assertEquals(2, a.getAssessmentCategory());
		assertEquals(3, a.getAssessmentId());
		assertEquals("Exam", a.getAssessmentType());
		assertEquals("Java", a.getAssessmentTitle());
		assertEquals(4, a.getBatchId());
		assertEquals(80, a.getRawScore());
		assertEquals(3, a.getWeekNumber());
	}

	@Test
	public void testConvertFromDTO() {

		AssessmentDTO as = new AssessmentDTO(2, 60, "Spring", "Verbal", 8, 7, 8);

		AssessmentConverter.convert(as);

		assertEquals(Integer.valueOf(2), as.getAssessmentId());
		assertEquals(Integer.valueOf(60), as.getRawScore());
		assertEquals("Spring", as.getAssessmentTitle());
		assertEquals("Verbal", as.getAssessmentType());
		assertEquals(Integer.valueOf(8), as.getWeekNumber());
		assertEquals(Integer.valueOf(7), as.getBatchId());
		assertEquals(Integer.valueOf(8), as.getAssessmentCategory());
	}
}
