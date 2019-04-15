package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.intercoms.TraineeClient;
import com.revature.caliber.repositories.AssessmentRepository;
import com.revature.caliber.repositories.GradeRepository;
import com.revature.caliber.services.AssessmentService;

@Service
public class GradeService implements GradeServiceInterface{
	
	Logger log = Logger.getLogger("GradeService.class");
	
	@Autowired
	private GradeRepository gp;
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private AssessmentRepository ar;
	
	@Autowired
	private AssessmentService as;

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
		if(g != null) contactTraineeService(g);
		
		return g;
	}

	@Override
	public Grade createGrade(Grade g) {
		log.debug("Creating Grade: " + g);
		return gp.save(g);
	}

	@Override
	public Grade updateGrade(Grade g) {
		log.debug("Updating Grade: " + g);
		return gp.save(g);
	}

	@Override
	public Boolean deleteGrade(Grade g) {
		log.debug("Deleting Grade: " + g);
		Boolean exists = false;
		if(gp.findOne(g.getGradeId()) != null) exists = true;
		if(exists) {
			gp.delete(g);
			return true;
		} else {
			return false;
		}
		
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

	@Override
	public List<Grade> findGradesByTraineeId(Integer id) {
		List<Grade> gradeList = gp.findGradesByTraineeId(id);
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
	public List<Grade> findGradesByAssessmentId(Integer id) {
		List<Grade> gradeList = gp.findGradesByAssessmentId(id);
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
	public Float findAvgAssessments(Integer id, Integer weekNum) {
		List<Assessment> assessments = as.findAssessmentsByBatchIdandWeekNumber(id, weekNum);
		List<Grade> grades =  new ArrayList<>();
		List<Grade> allgrade = this.findAllGrades();
		int temp = 0;
		float temp2 =0;
		for(Assessment a : assessments) {
		grades.addAll(allgrade.stream().filter(g->g.getAssessmentId()==a.getAssessmentId()).collect(Collectors.toList()));
		temp += a.getRawScore();
		}
		for(Grade gd : grades) {
			temp2 += gd.getScore();
		}
		
	
		return temp2/temp;

	}










	

}
