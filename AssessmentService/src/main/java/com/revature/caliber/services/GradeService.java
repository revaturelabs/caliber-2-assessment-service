package com.revature.caliber.services;

import java.util.List;

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
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			if(!contactTraineeService(g)) {
				for(int j = i; j < gradeList.size(); j++) {
					gradeList.get(j).setTraineeId(-1);
				}
				break;
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
			List<Trainee> response = tc.findAllTrainees();
			
			boolean found = false;
			
			if(response != null) {
				for(int i = 0; i < response.size(); i++) {
					if(response.get(i).getTraineeId().equals(g.getTraineeId())) {
						g.setTraineeId(response.get(i).getTraineeId());
						found = true;
					}
				}
			}
			
			if(!found) {
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
