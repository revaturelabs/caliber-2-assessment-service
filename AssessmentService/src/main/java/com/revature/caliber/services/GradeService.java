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
import com.revature.caliber.repositories.GradeRepository;

@Service
public class GradeService implements GradeServiceInterface{
	
	Logger log = Logger.getLogger("GradeService.class");
	
	@Autowired
	private GradeRepository gp;
	
	@Autowired
	private TraineeClient tc;

	@Override
	public List<Grade> findAllGrades() {
		List<Grade> gradeList = gp.findAll();
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			Integer tempGrade = g.getTraineeId();
			
			if(!alreadyConnected.containsKey(g.getTraineeId())) {
				if(contactTraineeService(g)) {
					alreadyConnected.put(tempGrade, true);
				} else {
					alreadyConnected.put(tempGrade, false);
				}
			}
			
			if(!alreadyConnected.get(tempGrade)) g.setTraineeId(-1);
			
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
			if(g.getTraineeId() != null) tc.findTraineeById(g.getTraineeId());
			
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
			Integer tempGrade = g.getTraineeId();
			
			if(!alreadyConnected.containsKey(g.getTraineeId())) {
				if(contactTraineeService(g)) {
					alreadyConnected.put(tempGrade, true);
				} else {
					alreadyConnected.put(tempGrade, false);
				}
			}
			
			if(!alreadyConnected.get(tempGrade)) g.setTraineeId(-1);
			
		}
		
		return gradeList;
	}

	@Override
	public List<Grade> findGradesByAssessmentId(Integer id) {
		List<Grade> gradeList = gp.findGradesByAssessmentId(id);
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			Integer tempGrade = g.getTraineeId();
			
			if(!alreadyConnected.containsKey(g.getTraineeId())) {
				if(contactTraineeService(g)) {
					alreadyConnected.put(tempGrade, true);
				} else {
					alreadyConnected.put(tempGrade, false);
				}
			}
			
			if(!alreadyConnected.get(tempGrade)) g.setTraineeId(-1);
			
		}
		
		return gradeList;
	}

	@Override
	public Float findAverageAssessment(Integer id) {
		List<Grade> grades = this.findGradesByAssessmentId(id);
		Float average = 0f;
		for(Grade g : grades) {
			average += g.getScore();
		}
		return (average/grades.size());
	}
	
	

}
