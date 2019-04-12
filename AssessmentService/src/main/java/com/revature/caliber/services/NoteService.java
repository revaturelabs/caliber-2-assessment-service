package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.BatchEntity;
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
	private NoteRepository gp;
	
	@Autowired
	private AssessmentRepository ap;
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private BatchClient bc;

	@Override
	public List<Note> findAllNotes() {
		
		List<Note> NoteList = gp.findAll();
		for(int i = 0; i < NoteList.size(); i++) {
			Note n = NoteList.get(i);
			
			contactTraineeService(n);
			contactBatchService(n);
		}
		
		return NoteList;
	}

	@Override
	public Note findNoteById(Integer id) {
		Note n = gp.findOne(id);
		contactTraineeService(n);
		contactBatchService(n);
		
		return n;
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
			List<Trainee> response = tc.findAllTrainees();
			
			boolean found = false;
			
			if(response != null) {
				for(int i = 0; i < response.size(); i++) {
					if(response.get(i).getTraineeId().equals(n.getTraineeId())) {
						n.setTraineeId(response.get(i).getTraineeId());
						found = true;
					}
				}
			}
			
			if(!found) {
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
