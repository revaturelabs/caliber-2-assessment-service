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
import com.revature.caliber.beans.Note;
import com.revature.caliber.controllers.NoteController;
import com.revature.caliber.converter.NoteConverter;
import com.revature.caliber.dto.NoteDTO;
import com.revature.caliber.exceptions.DoesNotExistException;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.services.NoteService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class NoteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private NoteService noteServiceMock;
	
	@InjectMocks
	NoteController noteController;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateNote() throws Exception{
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		Note noteObj = NoteConverter.convert(noteDTOObj);
		when(noteServiceMock.createNote(any(NoteDTO.class))).thenReturn(noteObj);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(post("/all/note/create").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.noteContent").value("testContent"))
				.andExpect(jsonPath("$.noteType").value("testType"))
				.andExpect(jsonPath("$.weekNumber").value(1))
				.andExpect(jsonPath("$.batchId").value(1))
				.andExpect(jsonPath("$.traineeId").value(1));
	}
	
	@Test
	public void testCreateNoteFail() throws Exception{
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		Note noteObj = NoteConverter.convert(noteDTOObj);
		when(noteServiceMock.createNote(any(NoteDTO.class))).thenThrow(new DuplicateException("Note already exists"));
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(post("/all/note/create").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Skill type already exists"));
	}
	
	@Test
	public void testFindAllNotes() throws Exception{
		List<Note> noteList = new ArrayList<>();
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		noteList.add(noteObj);
		when(noteServiceMock.findAllNotes()).thenReturn(noteList);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteList);
		mockMvc.perform(get("/all/note/all").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].noteContent").value("testContent"))
				.andExpect(jsonPath("$[0].noteType").value("testType"))
				.andExpect(jsonPath("$[0].weekNumber").value(1))
				.andExpect(jsonPath("$[0].batchId").value(1))
				.andExpect(jsonPath("$[0].traineeId").value(1));
	}
	
	@Test
	public void testFindAllNotesNull() throws Exception{
		
		when(noteServiceMock.findAllNotes()).thenReturn(null);
		
		String noteJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/note/all").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindNotesByTrainee() throws Exception{
		List<Note> noteList = new ArrayList<>();
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		
		int traineeId = 1;
		noteDTOObj.setTraineeId(traineeId);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		noteList.add(noteObj);
		when(noteServiceMock.findNotesByTraineeId(traineeId)).thenReturn(noteList);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteList);
		mockMvc.perform(get("/all/note/trainee/" + traineeId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].noteContent").value("testContent"))
				.andExpect(jsonPath("$[0].noteType").value("testType"))
				.andExpect(jsonPath("$[0].weekNumber").value(1))
				.andExpect(jsonPath("$[0].batchId").value(1))
				.andExpect(jsonPath("$[0].traineeId").value(traineeId));
	}
	
	@Test
	public void testFindNotesByTraineeNull() throws Exception{
		List<Note> noteList = new ArrayList<>();
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		
		int traineeId = 1;
		noteDTOObj.setTraineeId(traineeId);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		noteList.add(noteObj);
		when(noteServiceMock.findNotesByTraineeId(traineeId)).thenReturn(null);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteList);
		mockMvc.perform(get("/all/note/trainee/" + traineeId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void testUpdateNote() throws Exception{
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		Note noteObj = NoteConverter.convert(noteDTOObj);
		when(noteServiceMock.updateNote(any(NoteDTO.class))).thenReturn(noteObj);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(put("/all/note/update").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.noteContent").value("testContent"))
				.andExpect(jsonPath("$.noteType").value("testType"))
				.andExpect(jsonPath("$.weekNumber").value(1))
				.andExpect(jsonPath("$.batchId").value(1))
				.andExpect(jsonPath("$.traineeId").value(1));
	}
	
	@Test
	public void testUpdateNoteFail() throws Exception {
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		Note noteObj = NoteConverter.convert(noteDTOObj);
		when(noteServiceMock.updateNote(any(NoteDTO.class))).thenThrow(new DoesNotExistException("Note does not already exist"));
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(put("/all/note/update").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Category does not already exist"));
	}
	
	@Test
	public void testFindNotesByBatch() throws Exception{
		List<Note> noteList = new ArrayList<>();
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		
		int batchId = 1;
		noteDTOObj.setBatchId(batchId);
		noteDTOObj.setTraineeId(1);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		noteList.add(noteObj);
		when(noteServiceMock.findNotesByBatchId(batchId)).thenReturn(noteList);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteList);
		mockMvc.perform(get("/all/note/batch/" + batchId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].noteContent").value("testContent"))
				.andExpect(jsonPath("$[0].noteType").value("testType"))
				.andExpect(jsonPath("$[0].weekNumber").value(1))
				.andExpect(jsonPath("$[0].batchId").value(batchId))
				.andExpect(jsonPath("$[0].traineeId").value(1));
	}
	
	@Test
	public void testFindNotesByBatchNull() throws Exception{
		List<Note> noteList = new ArrayList<>();
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		
		int batchId = 1;
		noteDTOObj.setBatchId(batchId);
		noteDTOObj.setTraineeId(1);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		noteList.add(noteObj);
		when(noteServiceMock.findNotesByBatchId(batchId)).thenReturn(null);
		
		String noteJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/note/batch/" + batchId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindNotesById() throws Exception{
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		int noteId = 1;
		noteDTOObj.setNoteId(noteId);
		
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		
		Note noteObj = NoteConverter.convert(noteDTOObj);
		
		when(noteServiceMock.findNoteById(noteId)).thenReturn(noteObj);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(get("/all/note/" + noteId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.noteContent").value("testContent"))
				.andExpect(jsonPath("$.noteType").value("testType"))
				.andExpect(jsonPath("$.weekNumber").value(1))
				.andExpect(jsonPath("$.noteId").value(noteId))
				.andExpect(jsonPath("$.batchId").value(1))
				.andExpect(jsonPath("$.traineeId").value(1));
	}
	
	@Test
	public void testFindNotesByIdFail() throws Exception{
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		int noteId = 1;
		noteDTOObj.setNoteId(noteId);
		
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		
		when(noteServiceMock.findNoteById(noteId)).thenReturn(null);
		
		String noteJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(get("/all/note/" + noteId).contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteNote() throws Exception {
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		Note noteObj = NoteConverter.convert(noteDTOObj);
		when(noteServiceMock.deleteNote(any(NoteDTO.class))).thenReturn(true);
		
		String noteJson = new ObjectMapper().writeValueAsString(noteObj);
		mockMvc.perform(delete("/all/note/delete").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(true));
	}
	
	@Test
	public void testDeleteNoteFail() throws Exception {
		NoteDTO noteDTOObj = new NoteDTO();
		noteDTOObj.setNoteContent("testContent");
		noteDTOObj.setNoteType("testType");
		noteDTOObj.setWeekNumber(1);
		noteDTOObj.setBatchId(1);
		noteDTOObj.setTraineeId(1);
		
		when(noteServiceMock.deleteNote(any(NoteDTO.class))).thenReturn(false);
		
		String noteJson = new ObjectMapper().writeValueAsString(null);
		mockMvc.perform(delete("/all/note/delete").contentType(MediaType.APPLICATION_JSON).content(noteJson))
				.andExpect(status().isBadRequest());
	}
}
