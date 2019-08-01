package com.revature.caliber.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.converter.AssessmentConverter;
import com.revature.caliber.dto.AssessmentDTO;
import com.revature.caliber.services.AssessmentService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AssessmentController {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssessmentService assessmentServiceMock;

	@InjectMocks
	AssessmentController assessmentController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateAssessment() throws Exception {
		Assessment assessObj = new Assessment();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		
		when(assessmentServiceMock.createAssessment(any(AssessmentDTO.class))).thenReturn(assessObj);

		String assessJson = new ObjectMapper().writeValueAsString(assessObj);
		mockMvc.perform(post("/all/assessment/create").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.assessmentId").value(2))
				.andExpect(jsonPath("$.rawScore").value(75))
				.andExpect(jsonPath("$.assessmentTitle").value("Java"))
				.andExpect(jsonPath("$.assessmentType").value("Exam"))
				.andExpect(jsonPath("$.weekNumber").value(5))
				.andExpect(jsonPath("$.batchId").value(20))
				.andExpect(jsonPath("$.assessmentCategory").value(1));
	}
	
	@Test
	public void testCreateAssessmentFail() throws Exception {
		
		when(assessmentServiceMock.createAssessment(any(AssessmentDTO.class))).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(post("/all/assessment/create").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testFindAllAssessments() throws Exception {
		
		List<Assessment> assessList = new ArrayList<>();
		Assessment assessObj = new Assessment();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		assessList.add(assessObj);
		
		when(assessmentServiceMock.findAllAssessments()).thenReturn(assessList);

		String assessJson = new ObjectMapper().writeValueAsString(assessList);
		mockMvc.perform(get("/all/assessment/all").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].assessmentId").value(2))
				.andExpect(jsonPath("$[0].rawScore").value(75))
				.andExpect(jsonPath("$[0].assessmentTitle").value("Java"))
				.andExpect(jsonPath("$[0].assessmentType").value("Exam"))
				.andExpect(jsonPath("$[0].weekNumber").value(5))
				.andExpect(jsonPath("$[0].batchId").value(20))
				.andExpect(jsonPath("$[0].assessmentCategory").value(1));
	}
	
	@Test
	public void testFindAllAssessmentsNull() throws Exception {
		
		when(assessmentServiceMock.findAllAssessments()).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/assessment/all").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testfindAssessmentsByBatch() throws Exception {
		
		List<Assessment> assessList = new ArrayList<>();
		Assessment assessObj = new Assessment();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		
		int batchId = 20;
		assessObj.setBatchId(batchId);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		assessList.add(assessObj);
		
		when(assessmentServiceMock.findAssessmentsByBatchId(batchId)).thenReturn(assessList);

		String assessJson = new ObjectMapper().writeValueAsString(assessList);
		mockMvc.perform(get("/all/assessment/batch/" + batchId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].assessmentId").value(2))
				.andExpect(jsonPath("$[0].rawScore").value(75))
				.andExpect(jsonPath("$[0].assessmentTitle").value("Java"))
				.andExpect(jsonPath("$[0].assessmentType").value("Exam"))
				.andExpect(jsonPath("$[0].weekNumber").value(5))
				.andExpect(jsonPath("$[0].batchId").value(batchId))
				.andExpect(jsonPath("$[0].assessmentCategory").value(1));
	}
	
	@Test
	public void testfindAssessmentsByBatchNull() throws Exception {
		
		List<Assessment> assessList = new ArrayList<>();
		Assessment assessObj = new Assessment();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		
		int batchId = 20;
		assessObj.setBatchId(batchId);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		assessList.add(assessObj);
		
		when(assessmentServiceMock.findAssessmentsByBatchId(batchId)).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(assessList);
		mockMvc.perform(get("/all/assessment/batch/" + batchId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAssessmentsByCategory() throws Exception {
		
		List<Assessment> assessList = new ArrayList<>();
		Assessment assessObj = new Assessment();
		
		int assessCat = 1;
		assessObj.setAssessmentCategory(assessCat);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");		
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		assessList.add(assessObj);
		
		when(assessmentServiceMock.findAssessmentsByCategory(assessCat)).thenReturn(assessList);

		String assessJson = new ObjectMapper().writeValueAsString(assessList);
		mockMvc.perform(get("/all/assessment/category/" + assessCat).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].assessmentId").value(2))
				.andExpect(jsonPath("$[0].rawScore").value(75))
				.andExpect(jsonPath("$[0].assessmentTitle").value("Java"))
				.andExpect(jsonPath("$[0].assessmentType").value("Exam"))
				.andExpect(jsonPath("$[0].weekNumber").value(5))
				.andExpect(jsonPath("$[0].batchId").value(20))
				.andExpect(jsonPath("$[0].assessmentCategory").value(assessCat));
	}
	
	@Test
	public void findAssessmentsByCategoryNull() throws Exception {
		
		List<Assessment> assessList = new ArrayList<>();
		Assessment assessObj = new Assessment();
		
		int assessCat = 1;
		assessObj.setAssessmentCategory(assessCat);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");		
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		assessList.add(assessObj);
		
		when(assessmentServiceMock.findAssessmentsByCategory(assessCat)).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/assessment/category/" + assessCat).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAssessmentsById() throws Exception {
		
		Assessment assessObj = new Assessment();
		
		assessObj.setAssessmentCategory(1);
		
		int assessId = 2;
		assessObj.setAssessmentId(assessId);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");		
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		when(assessmentServiceMock.findAssessmentById(assessId)).thenReturn(assessObj);

		String assessJson = new ObjectMapper().writeValueAsString(assessObj);
		mockMvc.perform(get("/all/assessment/" + assessId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.assessmentId").value(assessId))
				.andExpect(jsonPath("$.rawScore").value(75))
				.andExpect(jsonPath("$.assessmentTitle").value("Java"))
				.andExpect(jsonPath("$.assessmentType").value("Exam"))
				.andExpect(jsonPath("$.weekNumber").value(5))
				.andExpect(jsonPath("$.batchId").value(20))
				.andExpect(jsonPath("$.assessmentCategory").value(1));
	}
	
	@Test
	public void findAssessmentsByIdNull() throws Exception {
		
		Assessment assessObj = new Assessment();
		
		assessObj.setAssessmentCategory(1);
		
		int assessId = 2;
		assessObj.setAssessmentId(assessId);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");		
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		when(assessmentServiceMock.findAssessmentById(assessId)).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(assessObj);
		mockMvc.perform(get("/all/assessment/" + assessId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testUpdateAssessment() throws Exception {
		AssessmentDTO assessObj = new AssessmentDTO();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		Assessment a = AssessmentConverter.convert(assessObj);
		when(assessmentServiceMock.updateAssessment(any(AssessmentDTO.class))).thenReturn(a);

		String assessJson = new ObjectMapper().writeValueAsString(a);
		mockMvc.perform(put("/all/assessment/update").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.assessmentId").value(2))
				.andExpect(jsonPath("$.rawScore").value(75))
				.andExpect(jsonPath("$.assessmentTitle").value("Java"))
				.andExpect(jsonPath("$.assessmentType").value("Exam"))
				.andExpect(jsonPath("$.weekNumber").value(5))
				.andExpect(jsonPath("$.batchId").value(20))
				.andExpect(jsonPath("$.assessmentCategory").value(1));
	}
	
	@Test
	public void testUpdateAssessmentFail() throws Exception {
		AssessmentDTO assessObj = new AssessmentDTO();
		assessObj.setAssessmentCategory(1);
		assessObj.setAssessmentId(2);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		when(assessmentServiceMock.updateAssessment(any(AssessmentDTO.class))).thenReturn(null);

		String assessJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(put("/all/assessment/update").contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeleteAssessment() throws Exception {
		AssessmentDTO assessObj = new AssessmentDTO();
		assessObj.setAssessmentCategory(1);
		
		int assessId = 2;
		assessObj.setAssessmentId(assessId);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		Assessment a = AssessmentConverter.convert(assessObj);
		when(assessmentServiceMock.deleteAssessment(any(AssessmentDTO.class))).thenReturn(true);

		String assessJson = new ObjectMapper().writeValueAsString(a);
		mockMvc.perform(delete("/all/assessment/delete/" + assessId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(true));

	}
	
	@Test
	public void testDeleteAssessmentFail() throws Exception {
		AssessmentDTO assessObj = new AssessmentDTO();
		assessObj.setAssessmentCategory(1);
		
		int assessId = 2;
		assessObj.setAssessmentId(assessId);
		assessObj.setAssessmentTitle("Java");
		assessObj.setAssessmentType("Exam");
		assessObj.setBatchId(20);
		assessObj.setRawScore(75);
		assessObj.setWeekNumber(5);
		
		when(assessmentServiceMock.deleteAssessment(any(AssessmentDTO.class))).thenReturn(false);

		String assessJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(delete("/all/assessment/delete/" + assessId).contentType(MediaType.APPLICATION_JSON).content(assessJson))
				.andExpect(status().isBadRequest());

	}
	

}
