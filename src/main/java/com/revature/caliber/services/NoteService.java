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

import java.util.*;
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
			int tempTrainee = n.getTraineeId();
			int tempBatch = n.getBatchId();
			
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
	public Note findNoteById(int id) {
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
//		if(np.findOne(n.getNoteId()) != null) return null;
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
	public boolean deleteNote(NoteDTO noteDTO) {
		Note n = NoteConverter.convert(noteDTO);
		log.debug("Deleing Note: " + n);
		boolean exists = false;
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
			if(n.getBatchId() >= 0) bc.getBatchById(n.getBatchId());
			
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
			if(n.getTraineeId() >=0) tc.findTraineeById(n.getTraineeId());
			
			return true;
		} catch(Exception e) {
			log.warn("Could not find Trainee with id: " + n.getTraineeId());
			log.warn(e.getMessage());
			n.setTraineeId(-1);
			return false;
		}
	}

	@Override
	public List<Note> findNotesByTraineeId(int id) {
		List<Note> noteList = np.findNotesByTraineeId(id);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public List<Note> findNotesByBatchId(int id) {
		List<Note> noteList = np.findNotesByBatchId(id);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public List<Note> findNotesByBatchIdAndWeekNumber(int bid, int weekNum) {
		List<Note> noteList = np.findNotesByBatchIdAndWeekNumber(bid, weekNum);
		
		return checkTraineeAndBatch(noteList);
	}

	@Override
	public Map<Integer, List<Note>> findNotesByBatchAndWeek(int batchId, int week) {
		Map<Integer, List<Note>> noteMap = new HashMap<>();
		Stream<Note> notes = np.findNotesByBatchIdAndWeekNumber(batchId, week).stream();
		notes.filter(note -> note.getTraineeId() >= 0 && note.getTraineeId() > 0).peek(note -> noteMap.putIfAbsent(note.getTraineeId(), new ArrayList<>())).forEach(note -> {
			noteMap.get(note.getTraineeId()).add(note);
		});
		return noteMap;
	}

	@Override
	public Optional<Note> findBatchNoteByBatchAndWeek(int batchId, int week) {
		return np.findBatchNoteByBatchIdAndWeekNumber(batchId, week);
	}

	@Override
	public Note upsertNote(NoteDTO noteDTO) {
		Note note = np.findOne(noteDTO.getNoteId());
		if (note != null) {
			note.setNoteContent(noteDTO.getNoteContent());
			note = np.save(note);
		} else {
			note = np.save(NoteConverter.convert(noteDTO));
		}
		return note;
	}
}
