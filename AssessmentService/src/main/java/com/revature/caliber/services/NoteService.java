package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.intercoms.BatchClient;
import com.revature.caliber.intercoms.TraineeClient;
import com.revature.caliber.repositories.NoteRepository;

@Service
public class NoteService implements NoteServiceInterface{
	
	Logger log = Logger.getLogger("NoteService.class");
	
	@Autowired
	private NoteRepository np;
	
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private BatchClient bc;
	
	@Override
	public List<Note> findAllNotes() {
		List<Note> noteList = np.findAll();
		Map<Integer, Boolean> traineeConected = new HashMap<>();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note n = noteList.get(i);
			Integer tempTrainee = n.getTraineeId();
			Integer tempBatch = n.getBatchId();
			
			if(!traineeConected.containsKey(n.getTraineeId())) {
				if(contactTraineeService(n)) {
					traineeConected.put(tempTrainee, true);
				} else {
					traineeConected.put(tempTrainee, false);
				}
			}
			if(!batchConnected.containsKey(n.getBatchId())) {
				if(contactBatchService(n)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			
			if(!traineeConected.get(tempTrainee)) n.setTraineeId(-1);
			if(!batchConnected.get(tempBatch)) n.setBatchId(-1);
			
		}
		
		return noteList;
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
	public Note createNote(Note n) {
		log.debug("Creating Note: " + n);
		if(np.findOne(n.getNoteId()) != null) return null;
		return np.save(n);
	}
	
	@Override
	public Note updateNote(Note n) {
		log.debug("Updating Note: " + n);
		return np.save(n);
	}

	@Override
	public Boolean deleteNote(Note n) {
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
		Map<Integer, Boolean> traineeConected = new HashMap<>();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note n = noteList.get(i);
			Integer tempTrainee = n.getTraineeId();
			Integer tempBatch = n.getBatchId();
			
			if(!traineeConected.containsKey(n.getTraineeId())) {
				if(contactTraineeService(n)) {
					traineeConected.put(tempTrainee, true);
				} else {
					traineeConected.put(tempTrainee, false);
				}
			}
			if(!batchConnected.containsKey(n.getBatchId())) {
				if(contactBatchService(n)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			
			if(!traineeConected.get(tempTrainee)) n.setTraineeId(-1);
			if(!batchConnected.get(tempBatch)) n.setBatchId(-1);
			
		}
		
		return noteList;
	}

	@Override
	public List<Note> findNotesByBatchId(Integer id) {
		List<Note> noteList = np.findNotesByBatchId(id);
		Map<Integer, Boolean> traineeConected = new HashMap<>();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note n = noteList.get(i);
			Integer tempTrainee = n.getTraineeId();
			Integer tempBatch = n.getBatchId();
			
			if(!traineeConected.containsKey(n.getTraineeId())) {
				if(contactTraineeService(n)) {
					traineeConected.put(tempTrainee, true);
				} else {
					traineeConected.put(tempTrainee, false);
				}
			}
			if(!batchConnected.containsKey(n.getBatchId())) {
				if(contactBatchService(n)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			
			if(!traineeConected.get(tempTrainee)) n.setTraineeId(-1);
			if(!batchConnected.get(tempBatch)) n.setBatchId(-1);
			
		}
		
		return noteList;
	}

	@Override
	public List<Note> findNotesByBatchIdAndWeekNumber(Integer bid, Integer weekNum) {
		List<Note> noteList = np.findNotesByBatchIdAndWeekNumber(bid, weekNum);
		
		Map<Integer, Boolean> traineeConected = new HashMap<>();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note n = noteList.get(i);
			Integer tempTrainee = n.getTraineeId();
			Integer tempBatch = n.getBatchId();
			
			if(!traineeConected.containsKey(n.getTraineeId())) {
				if(contactTraineeService(n)) {
					traineeConected.put(tempTrainee, true);
				} else {
					traineeConected.put(tempTrainee, false);
				}
			}
			if(!batchConnected.containsKey(n.getBatchId())) {
				if(contactBatchService(n)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			
			if(!traineeConected.get(tempTrainee)) n.setTraineeId(-1);
			if(!batchConnected.get(tempBatch)) n.setBatchId(-1);
			
		}
		
		return noteList;
	}
	

}
