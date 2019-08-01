package com.revature.caliber.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.controllers.GradeController;
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
}
