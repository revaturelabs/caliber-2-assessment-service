package com.revature.tests;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.dto.AssessmentDTO;
import com.revature.caliber.dto.GradeDTO;
import com.revature.caliber.services.AssessmentService;
import com.revature.caliber.services.GradeService;

public class ServiceTest {
	AssessmentService assessmentService = mock(AssessmentService.class);
	GradeService gradeService = mock(GradeService.class);
	
	List<Assessment> assessmentsList = new ArrayList<>();
	List<Assessment> batchAssessmentsList = new ArrayList<>();
	List<Assessment> categoryAssessmentsList = new ArrayList<>();
	List<Assessment> batchAndWeekNumAssessmentsList = new ArrayList<>();
	List<Grade> gradesList = new ArrayList<>();
	
	@Before
	public void setup() {
		//AssessmentService tests setup 
		Assessment a1 = new Assessment(12, 74, "a1", "java", 2, 84, 1);
		AssessmentDTO a1DTO = new AssessmentDTO(12, 74, "a1", "java", 2, 84, 1);
		Assessment a2 = new Assessment(17, 20, "a2", "sales force", 7, 31, 2);
		AssessmentDTO a2DTO = new AssessmentDTO(17, 20, "a2", "sales force", 7, 31, 2);
		Assessment a3 = new Assessment(20, 97, "a3", "big data", 5, 9, 3);
		AssessmentDTO a3DTO = new AssessmentDTO(20, 97, "a3", "big data", 5, 9, 3);
		Assessment a4 = new Assessment(22, 87, "a4", "java", 2, 84, 9);
		
		assessmentsList.add(a1);
		assessmentsList.add(a2);
		assessmentsList.add(a3);
		assessmentsList.add(a4);
		
		batchAssessmentsList.add(a1);
		batchAssessmentsList.add(a4);
		
		categoryAssessmentsList.add(a4);
		
		batchAndWeekNumAssessmentsList.add(a1);
		batchAndWeekNumAssessmentsList.add(a4);
		
		when(assessmentService.findAllAssessments()).thenReturn(assessmentsList);
		when(assessmentService.findAssessmentById(17)).thenReturn(a2);
		when(assessmentService.deleteAssessment(a3DTO)).thenReturn(true);
		when(assessmentService.createAssessment(a1DTO)).thenReturn(a1);
		when(assessmentService.createAssessment(a3DTO)).thenReturn(null);
		when(assessmentService.updateAssessment(a2DTO)).thenReturn(a2);
		when(assessmentService.findAssessmentsByBatchId(84)).thenReturn(batchAssessmentsList);
		when(assessmentService.findAssessmentsByCategory(9)).thenReturn(categoryAssessmentsList);
		when(assessmentService.findAssessmentsByBatchIdAndWeekNumber(84, 2)).thenReturn(batchAndWeekNumAssessmentsList);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = dateFormat.parse("13/02/2018");
			Grade g1 = new Grade(2, date1, 79.12f, 12, 36);
			Date date2 = dateFormat.parse("17/03/2018");
			Grade g2 = new Grade(3, date2, 99.2f, 13, 47);
			Date date3 = dateFormat.parse("23/03/2018");
			Grade g3 = new Grade(4, date3, 83.9f, 13, 49);
			Date date = dateFormat.parse("17/03/2018");
			
			gradesList.add(g1);
			gradesList.add(g2);
			gradesList.add(g3);
			
			when(gradeService.findAllGrades()).thenReturn(gradesList);
			when(gradeService.findGradeById(3)).thenReturn(g2);
			when(gradeService.createGrade(new GradeDTO(7, date, 74.4f, 19, 73))).thenReturn(new Grade(7, date, 74.4f, 19, 73));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindAllAssessmentsMethod() {
		assertThat(assessmentService.findAllAssessments(), hasItems(
				new Assessment(12, 74, "a1", "java", 2, 84, 1),
				new Assessment(17, 20, "a2", "sales force", 7, 31, 2),
				new Assessment(20, 97, "a3", "big data", 5, 9, 3),
				new Assessment(22, 87, "a4", "java", 2, 84, 9)
		));
	}
	
	@Test
	public void testFindAssessmentByIdMethodWithExpectedInput() {
		assertEquals(new Assessment(17, 20, "a2", "sales force", 7, 31, 2) ,assessmentService.findAssessmentById(17));
	}
	
	@Test
	public void testFindAssessmentByIdMethodWithNonExistantId() {
		assertEquals(null, assessmentService.findAssessmentById(2));
	}
	
	@Test
	public void testFindAssessmentByIdMethodWithInvalidInput() {
		assertEquals(null, assessmentService.findAssessmentById((int) 'd'));
	}
	
	@Test
	public void testDeleteAssessmentWithExistingAssessment() {
		assertTrue(assessmentService.deleteAssessment(new AssessmentDTO(20, 97, "a3", "big data", 5, 9, 3)));
	}
	
	@Test
	public void testDeleteAssessmentWithNonExistantInput() {
		assertFalse(assessmentService.deleteAssessment(new AssessmentDTO(0, 0, "", "", 0, 0, 0)));
	}
	
	@Test
	public void testCreateAssessmentWithNewAssessment() {
		assertEquals(new Assessment(12, 74, "a1", "java", 2, 84, 1), assessmentService.createAssessment(new AssessmentDTO(12, 74, "a1", "java", 2, 84, 1)));
	}
	
	@Test
	public void testCreateAssessmentWithAlreadyExistingAssessment() {
		assertEquals(null, assessmentService.createAssessment(new AssessmentDTO(20, 97, "a3", "big data", 5, 9, 3)));
	}
	
	@Test
	public void testUpdateAssessmentWithExistingAssessment() {
		assertEquals(new Assessment(17, 20, "a2", "sales force", 7, 31, 2), assessmentService.updateAssessment(new AssessmentDTO(17, 20, "a2", "sales force", 7, 31, 2)));
	}
	
	@Test
	public void testUpdateAssessmentWithNonExistantAssessment() {
		assertEquals(null, assessmentService.updateAssessment(new AssessmentDTO(32, 84, "boo", "none", 4, 16, 8)));
	}
	
	@Test
	public void testFindAssessmentsByBatchIdWithExpectedInput() {
		assertThat(assessmentService.findAssessmentsByBatchId(84), hasItems(
				new Assessment(12, 74, "a1", "java", 2, 84, 1),
				new Assessment(22, 87, "a4", "java", 2, 84, 9)
		));
	}
	
	@Test
	public void testFindAssessmentsByBatchIdWithNonExistantId() {
		assertEquals(null, assessmentService.findAssessmentById(847932));
	}
	
	@Test
	public void testFindAssessmentsByBatchIdWithInvalidInput() {
		assertEquals(null, assessmentService.findAssessmentById((int) 'c'));
	}
	
	//test AssessmentService.findAssessmentsByCategory method
	@Test
	public void testFindAssessmentsByCategoryWithExpectedInput() {
		assertThat(assessmentService.findAssessmentsByCategory(9), hasItems(
				new Assessment(22, 87, "a4", "java", 2, 84, 9)
		));
	}
	
	@Test
	public void testFindAssessmentsByCategoryWithNonExistantId() {
		List<Assessment> emptyList = new ArrayList<>();
		assertEquals(emptyList, assessmentService.findAssessmentsByBatchId(29384));
	}
	
	//test AssessmentService.findAssessmentsByBatchIdAndWeekNumber method
	@Test
	public void testFindAssessmentsByBatchIdAndWeekNumberWithExpectedInputs() {
		assertThat(assessmentService.findAssessmentsByBatchIdAndWeekNumber(84, 2), hasItems(
				new Assessment(12, 74, "a1", "java", 2, 84, 1),
				new Assessment(22, 87, "a4", "java", 2, 84, 9)
		));
	}
	
	@Test
	public void testFindAssessmentsByBatchIdAndWeekNumberWithNonExistantInputs() {
		List<Assessment> emptyList = new ArrayList<>();
		assertEquals(emptyList, assessmentService.findAssessmentsByBatchIdAndWeekNumber(3471023, 89745816));
	}
	
	@Test
	public void testFindAllGrades() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = dateFormat.parse("13/02/2018");
			Date date2 = dateFormat.parse("17/03/2018");
			Date date3 = dateFormat.parse("23/03/2018");		
			assertThat(gradeService.findAllGrades(), hasItems(
				new Grade(2, date1, 79.12f, 12, 36),
				new Grade(3, date2, 99.2f, 13, 47),
				new Grade(4, date3, 83.9f, 13, 49)
			));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindGradeByIdWithExpectedInput() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date2 = dateFormat.parse("17/03/2018");
			assertEquals(new Grade(3, date2, 99.2f, 13, 47), gradeService.findGradeById(3));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindGradeByIdWithNonExistantId() {
		assertEquals(null, gradeService.findGradeById(8749156));
	}
	
	@Test
	public void testCreateGradeWithExpectedInput() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = dateFormat.parse("17/03/2018");
			assertEquals(new Grade(7, date, 74.4f, 19, 73), gradeService.createGrade(new GradeDTO(7, date, 74.4f, 19, 73)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
