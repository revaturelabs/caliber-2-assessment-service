package com.revature.caliber.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.controllers.GradeController;
import com.revature.caliber.converter.GradeConverter;
import com.revature.caliber.dto.GradeDTO;
import com.revature.caliber.services.GradeService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class GradeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GradeService gradeServiceMock;

	@InjectMocks
	GradeController gradeController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateGrade() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(5);
		
		when(gradeServiceMock.createGrade(any(GradeDTO.class))).thenReturn(gradeObj);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(post("/all/grade/create").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.gradeId").value("7"))
				.andExpect(jsonPath("$.dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$.score").value(80))
				.andExpect(jsonPath("$.assessmentId").value(1))
				.andExpect(jsonPath("$.traineeId").value(5));
	}
	
	@Test
	public void testFindAllGrades() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(5);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findAllGrades()).thenReturn(gradesList);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/all").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].gradeId").value("7"))
				.andExpect(jsonPath("$[0].dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$[0].score").value(80))
				.andExpect(jsonPath("$[0].assessmentId").value(1))
				.andExpect(jsonPath("$[0].traineeId").value(5));
	}
	
	@Test
	public void testFindAllGradesNull() throws Exception {

		when( gradeServiceMock.findAllGrades()).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/grade/all").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindGradesByTrainee() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		int traineeId = 5;
		gradeObj.setTraineeId(traineeId);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findGradesByTraineeId(traineeId)).thenReturn(gradesList);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/trainee/"+ traineeId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].gradeId").value(7))
				.andExpect(jsonPath("$[0].dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$[0].score").value(80))
				.andExpect(jsonPath("$[0].assessmentId").value(1))
				.andExpect(jsonPath("$[0].traineeId").value(traineeId));
	}
	
	@Test
	public void findGradesByAssessment() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		int assessmentId = 5;
		gradeObj.setAssessmentId(assessmentId);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findGradesByAssessmentId(assessmentId)).thenReturn(gradesList);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/assessment/"+ assessmentId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].gradeId").value(7))
				.andExpect(jsonPath("$[0].dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$[0].score").value(80))
				.andExpect(jsonPath("$[0].assessmentId").value(assessmentId))
				.andExpect(jsonPath("$[0].traineeId").value(1));
	}
	
	@Test
	public void testUpdateGrade() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		GradeDTO gradeObj = new GradeDTO();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(5);
		
		Grade g = GradeConverter.convert(gradeObj);
		
		when( gradeServiceMock.updateGrade(any(GradeDTO.class))).thenReturn(g);

		String gradeJson = new ObjectMapper().writeValueAsString(g);
		mockMvc.perform(put("/all/grade/update").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.gradeId").value("7"))
				.andExpect(jsonPath("$.dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$.score").value(80))
				.andExpect(jsonPath("$.assessmentId").value(1))
				.andExpect(jsonPath("$.traineeId").value(5));
	}

	
	@Test
	public void findGradeById() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		
		gradeObj.setAssessmentId(5);
		gradeObj.setDateReceived(date1);
		Integer gradeId = 7;
		gradeObj.setGradeId(gradeId);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		when( gradeServiceMock.findGradeById(gradeId)).thenReturn(gradeObj);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/"+ gradeId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.gradeId").value(gradeId))
				.andExpect(jsonPath("$.dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$.score").value(80))
				.andExpect(jsonPath("$.assessmentId").value(5))
				.andExpect(jsonPath("$.traineeId").value(1));
	}
	
	@Test
	public void findGradeByIdNull() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		
		gradeObj.setAssessmentId(5);
		gradeObj.setDateReceived(date1);
		Integer gradeId = 7;
		gradeObj.setGradeId(gradeId);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		when( gradeServiceMock.findGradeById(gradeId)).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/"+ gradeId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindGradesByTraineeNull() throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		int traineeId = 5;
		gradeObj.setTraineeId(traineeId);
		
		when( gradeServiceMock.findGradesByTraineeId(traineeId)).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/trainee/"+ traineeId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void findGradesByAssessmentNull() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		Grade gradeObj = new Grade();
		int assessmentId = 5;
		gradeObj.setAssessmentId(assessmentId);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findGradesByAssessmentId(assessmentId)).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/grade/assessment/"+ assessmentId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testUpdateGradeFail() throws Exception {
		
		when( gradeServiceMock.updateGrade(any(GradeDTO.class))).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(put("/all/grade/update").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void findGradesByBatch() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		
		BatchEntity batch = new BatchEntity();
		
		Integer batchId = 100;
		batch.setBatchId(batchId);
		Integer weeks = 5;
		batch.setWeeks(weeks);		
		
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(5);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findGradesByBatchId(batchId)).thenReturn(gradesList);

		String gradeJson = new ObjectMapper().writeValueAsString(gradeObj);
		mockMvc.perform(get("/all/grade/batch/"+ batchId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].gradeId").value(7))
				.andExpect(jsonPath("$[0].dateReceived").value("2018-02-13"))
				.andExpect(jsonPath("$[0].score").value(80))
				.andExpect(jsonPath("$[0].assessmentId").value(5))
				.andExpect(jsonPath("$[0].traineeId").value(1));
	}
	
	@Test
	public void findGradesByBatchNull() throws Exception {
		List<Grade> gradesList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		
		BatchEntity batch = new BatchEntity();
		
		Integer batchId = 100;
		batch.setBatchId(batchId);
		Integer weeks = 5;
		batch.setWeeks(weeks);	
		
		Grade gradeObj = new Grade();
		gradeObj.setAssessmentId(5);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		
		gradeObj.setTraineeId(1);
		
		gradesList.add(gradeObj);
		when( gradeServiceMock.findGradesByBatchId(batchId)).thenReturn(null);

		String gradeJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/grade/batch/"+ batchId).contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteGrade() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		GradeDTO gradeObj = new GradeDTO();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(5);
		
		Grade g = GradeConverter.convert(gradeObj);
		
		when( gradeServiceMock.deleteGrade(any(GradeDTO.class))).thenReturn(true);

		String gradeJson = new ObjectMapper().writeValueAsString(g);
		mockMvc.perform(delete("/all/grade/delete").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(true));

	}
	
	@Test
	public void testDeleteGradeFail() throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse("13/02/2018");
		GradeDTO gradeObj = new GradeDTO();
		gradeObj.setAssessmentId(1);
		gradeObj.setDateReceived(date1);
		gradeObj.setGradeId(7);
		gradeObj.setScore(80);
		gradeObj.setTraineeId(5);
		
		when( gradeServiceMock.deleteGrade(any(GradeDTO.class))).thenReturn(false);

		String gradeJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(delete("/all/grade/delete").contentType(MediaType.APPLICATION_JSON).content(gradeJson))
		.andExpect(status().isBadRequest());
	}
}
