package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.intercoms.TraineeClient;
import com.revature.caliber.repositories.AssessmentRepository;
import com.revature.caliber.repositories.GradeRepository;

@Service
public class GradeService implements GradeServiceInterface{
	
	Logger log = Logger.getLogger("GradeService.class");
	
	@Autowired
	private GradeRepository gp;
	
	@Autowired
	private AssessmentRepository ap;
	
	@Autowired
	private TraineeClient tc;

	@Override
	public List<Grade> findAllGrades() {
		List<Grade> gradeList = gp.findAll();
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			
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
		
		return gradeList;
	}

	@Override
	public Grade findGradeById(Integer id) {
		Grade g = gp.findOne(id);
		contactTraineeService(g);
		
		return g;
	}
	
	private boolean contactTraineeService(Grade g) {
		try {
			Trainee response = tc.findTraineeById(g.getTraineeId());
			
			if(response == null) {
				g.setTraineeId(-1);
			}
			
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with TraineeService");
			log.warn(e.getMessage());
			g.setTraineeId(-1);
			return false;
		}
	}
	

}
