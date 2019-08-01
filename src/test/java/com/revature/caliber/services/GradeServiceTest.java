package com.revature.caliber.services;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



import com.revature.caliber.beans.Grade;


import com.revature.caliber.repositories.GradeRepository;


public class GradeServiceTest {
	
	
	GradeRepository gradeRepository = mock(GradeRepository.class);
	List<Grade> gradesList = new ArrayList<Grade>();
	List<Grade> gradesList2 = new ArrayList<Grade>();
	
	@Before
	public void setup() {
		
		

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = dateFormat.parse("13/02/2018");
			Grade g1 = new Grade(2, date1, 79.12f, 12, 36);
			Date date2 = dateFormat.parse("17/03/2018");
			Grade g2 = new Grade(3, date2, 99.2f, 13, 47);
			Date date3 = dateFormat.parse("23/03/2018");
			Grade g3 = new Grade(4, date3, 83.9f, 13, 49);

			Grade g4 = new Grade(5, date1, 83.9f, 12, 36);

			
			gradesList2.add(g1);
			gradesList2.add(g4);
			
			gradesList.add(g1);
			gradesList.add(g2);
			gradesList.add(g3);
			
			when(gradeRepository.findGradesByTraineeId(36)).thenReturn(gradesList2);
			
			when(gradeRepository.findGradesByAssessmentId(12)).thenReturn(gradesList2);
			
			when(gradeRepository.findGradesByTraineeId(8765)).thenReturn(null);
			
			when(gradeRepository.findGradesByAssessmentId(6666)).thenReturn(null);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	

	
	@Test
	public void testFindGradesByTraineeIdWithExpectedInput() {
		assertEquals(gradesList2, gradeRepository.findGradesByTraineeId(36));
	}
	
	@Test
	public void testFindGradesByTraineeIdWithInvalidInput() {
		assertEquals(null, gradeRepository.findGradesByTraineeId(8765));
	}
	

	@Test
	public void testFindGradesByAssessmentIdExpectedInput() {
		assertEquals(gradesList2, gradeRepository.findGradesByAssessmentId(12));
	}
	
	@Test
	public void testFindGradesByAssessmentIdInvalidInput() {
		assertEquals(null, gradeRepository.findGradesByAssessmentId(6666));
	}
	
	

}
