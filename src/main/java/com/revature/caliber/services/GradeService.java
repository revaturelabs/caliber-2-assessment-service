package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.converter.GradeConverter;
import com.revature.caliber.dto.GradeDTO;
import com.revature.caliber.intercoms.TraineeClient;
import com.revature.caliber.repositories.GradeRepository;

@Service
public class GradeService implements GradeServiceInterface{
	
	Logger log = Logger.getLogger("GradeService.class");
	
	@Autowired
	private GradeRepository gp;
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private AssessmentService as;

	@Override
	public List<Grade> findAllGrades() {
		List<Grade> gradeList = gp.findAll();
		
		return checkTrainee(gradeList);
	}

	@Override
	public Grade findGradeById(Integer id) {
		Grade g = gp.findOne(id);
		if(g != null) contactTraineeService(g);
		
		return g;
	}

	@Override
	public Grade createGrade(GradeDTO grade) {
		Grade g = GradeConverter.convert(grade);
		log.debug("Creating Grade: " + g);
		if(gp.findOne(g.getGradeId()) != null) return null;

		return  gp.save(g);
	}

	@Override
	public Grade updateGrade(GradeDTO grade) {
		Grade g = GradeConverter.convert(grade);
		log.debug("Updating Grade: " + g);
		if(gp.findOne(g.getGradeId()) == null) return null;
		return gp.save(g);
	}

	@Override
	public Boolean deleteGrade(GradeDTO grade) {
		Grade g = GradeConverter.convert(grade);
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
		
		return checkTrainee(gradeList);
	}

	@Override
	public List<Grade> findGradesByAssessmentId(Integer id) {
		List<Grade> gradeList = gp.findGradesByAssessmentId(id);
		
		return checkTrainee(gradeList);
	}
	
	@Override
	public List<Grade> findGradesByBatchIdAndWeekNumber(Integer id, Integer weekNumber) {
		List<Assessment> assessmentList = as.findAssessmentsByBatchIdAndWeekNumber(id, weekNumber);
		List<Integer> assessmentIds = new ArrayList<>();
		for(Assessment a : assessmentList) {
			assessmentIds.add(a.getAssessmentId());
		}
		List<Grade> gradeList = gp.findGradesByAssessmentIdIn(assessmentIds);
		
		return checkTrainee(gradeList);
	}
	
	@Override
	public List<Grade> findGradesByBatchId(Integer id) {
		List<Assessment> assessmentList = as.findAssessmentsByBatchId(id);
		List<Integer> assessmentIds = new ArrayList<>();
		for(Assessment a : assessmentList) {
			assessmentIds.add(a.getAssessmentId());
		}
		List<Grade> gradeList = gp.findGradesByAssessmentIdIn(assessmentIds);
		
		return checkTrainee(gradeList);
	}

	@Override
	public Float findAvgAssessments(Integer id, Integer weekNum) {
		List<Assessment> assessments = as.findAssessmentsByBatchIdAndWeekNumber(id, weekNum);
		int temp = 0;
		float temp2 =0;
		for(Assessment a : assessments) {
			List<Grade> grades = this.findGradesByAssessmentId(a.getAssessmentId());
			for(Grade gd : grades) {
				temp2 += (gd.getScore()/100)*a.getRawScore();
			}
			temp += a.getRawScore()*grades.size();
			
		}
		if(temp != 0) return (temp2/temp)*100;
		else return null;


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
  
  private List<Grade> checkTrainee(List<Grade> gradeList){
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			Integer tempTrainee = g.getTraineeId();
			
			if(!alreadyConnected.containsKey(g.getTraineeId())) {
				boolean result = false;
				if(contactTraineeService(g)) {
					result = true;
				}
				alreadyConnected.put(tempTrainee, result);
			}
			
			if(!alreadyConnected.get(tempTrainee)) g.setTraineeId(-1);
			
		}
		
		return gradeList;
	}

}
