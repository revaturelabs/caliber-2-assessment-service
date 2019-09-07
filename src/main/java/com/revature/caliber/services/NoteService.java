package com.revature.caliber.services;

import com.revature.caliber.beans.Note;
import com.revature.caliber.converter.NoteConverter;
import com.revature.caliber.dto.NoteDTO;
import com.revature.caliber.intercoms.base.BatchClient;
import com.revature.caliber.intercoms.base.TraineeClient;
import com.revature.caliber.repositories.NoteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NoteService implements NoteServiceInterface{
	
	Logger log = Logger.getLogger("NoteService.class");
	
	@Autowired
	private NoteRepository np;
	
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private BatchClient bc;
	
	private List<Note> checkTraineeAndBatch(List<Note> noteList){
		Map<Integer, Boolean> traineeConected = new HashMap<>();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note n = noteList.get(i);
			Integer tempTrainee = n.getTraineeId();
			Integer tempBatch = n.getBatchId();
			
			if(!traineeConected.containsKey(n.getTraineeId())) {
				boolean result = false;
				if(contactTraineeService(n)) {
					result = true;
				}
				traineeConected.put(tempTrainee, result);
			}
			if(!batchConnected.containsKey(n.getBatchId())) {
				boolean result = false;
				if(contactBatchService(n)) {
					result = true;
				}
				batchConnected.put(tempBatch, result);
			}
			
			if(!traineeConected.get(tempTrainee)) n.setTraineeId(-1);
			if(!batchConnected.get(tempBatch)) n.setBatchId(-1);
			
		}
		
		return noteList;
	}
	
	@Override
	public List<Note> findAllNotes() {
		List<Note> noteList = np.findAll();
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public Note findNoteById(Integer id) {
		Note n = np.findOne(id);
		if(n!= null) {
			contactTraineeService(n);
			contactBatchService(n);
		}
		
		return n;
	}
	
	@Override
	public Note createNote(NoteDTO noteDTO) {
		Note n = NoteConverter.convert(noteDTO);
		log.debug("Creating Note: " + n);
		if(np.findOne(n.getNoteId()) != null) return null;
		return np.save(n);
	}
	
	@Override
	public Note updateNote(NoteDTO noteDTO) {
		Note n = NoteConverter.convert(noteDTO);
		log.debug("Updating Note: " + n);
		if(np.findOne(n.getNoteId()) == null) return null;
		return np.save(n);
	}

	@Override
	public Boolean deleteNote(NoteDTO noteDTO) {
		Note n = NoteConverter.convert(noteDTO);
		log.debug("Deleing Note: " + n);
		Boolean exists = false;
		if(np.findOne(n.getNoteId()) != null) exists = true;  
		if(exists) {
			np.delete(n);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean contactBatchService(Note n) {
		try {
			if(n.getBatchId() != null) bc.getBatchById(n.getBatchId());
			
			return true;
		} catch(Exception e) {
			log.warn("Could not find Batch with id: " + n.getBatchId());
			log.warn(e.getMessage());
			n.setBatchId(-1);
			return false;
		}
	}
	
	private boolean contactTraineeService(Note n) {
		try {
			if(n.getTraineeId() != null) tc.findTraineeById(n.getTraineeId());
			
			return true;
		} catch(Exception e) {
			log.warn("Could not find Trainee with id: " + n.getTraineeId());
			log.warn(e.getMessage());
			n.setTraineeId(-1);
			return false;
		}
	}

	@Override
	public List<Note> findNotesByTraineeId(Integer id) {
		List<Note> noteList = np.findNotesByTraineeId(id);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public List<Note> findNotesByBatchId(Integer id) {
		List<Note> noteList = np.findNotesByBatchId(id);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public List<Note> findNotesByBatchIdAndWeekNumber(Integer bid, Integer weekNum) {
		List<Note> noteList = np.findNotesByBatchIdAndWeekNumber(bid, weekNum);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public Map<Integer, List<Note>> findNotesByBatchAndWeek(Integer batchId, Integer week) {
		Map<Integer, List<Note>> noteMap = new HashMap<>();
		Stream<Note> notes = np.findNotesByBatchIdAndWeekNumber(batchId, week).stream();
		notes.peek(note -> noteMap.putIfAbsent(note.getTraineeId(), new ArrayList<>())).forEach(note -> {
			noteMap.get(note.getTraineeId()).add(note);
		});
		return noteMap;
	}
}
