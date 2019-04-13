package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.intercoms.BatchClient;
import com.revature.caliber.intercoms.TraineeClient;
import com.revature.caliber.repositories.AssessmentRepository;
import com.revature.caliber.repositories.NoteRepository;

@Service
public class NoteService implements NoteServiceInterface{
	
	Logger log = Logger.getLogger("NoteService.class");
	
	@Autowired
	private NoteRepository np;
	
	@Autowired
	private AssessmentRepository ap;
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private BatchClient bc;
	
	@Override
	public List<Note> findAllNotes() {
		List<Note> noteList = np.findAll();
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < noteList.size(); i++) {
			Note g = noteList.get(i);
			
			if(!alreadyConnected.containsKey(g.getTraineeId())) {
				if(contactTraineeService(g)) {
					alreadyConnected.put(g.getTraineeId(), true);
				} else {
					alreadyConnected.put(g.getTraineeId(), false);
				}
			}
			
			if(!alreadyConnected.get(g.getTraineeId())) {
				g.setTraineeId(-1);
			}
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
		return np.save(n);
	}
	
	private boolean contactBatchService(Note n) {
		try {
			BatchEntity response = bc.getBatchById(n.getBatchId());
			
			if(response != null) {
				n.setBatchId(response.getBatchId());
			} else {
				n.setBatchId(-1);
			}
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with BatchService");
			log.warn(e.getMessage());
			n.setBatchId(-1);
			return false;
		}
	}
	
	private boolean contactTraineeService(Note n) {
		try {
			Trainee response = tc.findTraineeById(n.getTraineeId());
			
			if(response == null) {
				n.setTraineeId(-1);
			}
			
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with TraineeService");
			log.warn(e.getMessage());
			n.setTraineeId(-1);
			return false;
		}
	}
	

}
