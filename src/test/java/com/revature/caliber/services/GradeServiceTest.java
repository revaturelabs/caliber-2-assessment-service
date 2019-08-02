package com.revature.caliber.services;



import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.repositories.GradeRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class GradeServiceTest {

	@MockBean
	private GradeRepository gradeRepositoryMock;

	@InjectMocks
	GradeService gradeServiceMock;
	
	
	GradeRepository gradeRepository = mock(GradeRepository.class);
	List<Grade> gradesList = new ArrayList<Grade>();
	List<Grade> gradesList2 = new ArrayList<Grade>();
	
//	@Before
//	public void setup() {
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			Date date1 = dateFormat.parse("13/02/2018");
//			Grade g1 = new Grade(2, date1, 79.12f, 12, 36);
//			Date date2 = dateFormat.parse("17/03/2018");
//			Grade g2 = new Grade(3, date2, 99.2f, 13, 47);
//			Date date3 = dateFormat.parse("23/03/2018");
//			Grade g3 = new Grade(4, date3, 83.9f, 13, 49);
//
//			Grade g4 = new Grade(5, date1, 83.9f, 12, 36);
//
//			
//			gradesList2.add(g1);
//			gradesList2.add(g4);
//			
//			gradesList.add(g1);
//			gradesList.add(g2);
//			gradesList.add(g3);
//			
//			when(gradeRepository.findGradesByTraineeId(36)).thenReturn(gradesList2);
//			
//			when(gradeRepository.findGradesByAssessmentId(12)).thenReturn(gradesList2);
//			
//			when(gradeRepository.findGradesByTraineeId(8765)).thenReturn(null);
//			
//			when(gradeRepository.findGradesByAssessmentId(6666)).thenReturn(null);
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAllGrades() throws ParseException {
		
		List<Grade> gradeList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(1);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(1);
		
		gradeList.add(gradeObj);
		
		when(gradeServiceMock.findAllGrades()).thenReturn(gradeList);
		
		List<Grade> findAllGrades = gradeServiceMock.findAllGrades();
		assertEquals(gradeList.size(), findAllGrades.size());
		Grade grade = findAllGrades.get(0);
		assertEquals(gradeObj.getAssessmentId(), grade.getAssessmentId());
		
	}
	
	@Test
	public void testFindGradeById() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		int gradeId = 1;
		gradeObj.setGradeId(gradeId);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(1);
		
		
		when(gradeServiceMock.findGradeById(gradeId)).thenReturn(gradeObj);
		when(gradeRepositoryMock.findOne(gradeId)).thenReturn(gradeObj);
		
		Grade findGradeId = gradeServiceMock.findGradeById(gradeId);
		Grade g = gradeRepositoryMock.findOne(gradeId);
	
		assertEquals(g.getAssessmentId(), findGradeId.getAssessmentId());
		
	}
	

}
