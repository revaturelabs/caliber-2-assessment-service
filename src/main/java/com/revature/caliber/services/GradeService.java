package com.revature.caliber.services;

import com.revature.caliber.beans.*;
import com.revature.caliber.converter.GradeConverter;
import com.revature.caliber.dto.*;
import com.revature.caliber.intercoms.base.CategoryClient;
import com.revature.caliber.intercoms.base.TraineeClient;
import com.revature.caliber.repositories.GradeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GradeService implements GradeServiceInterface{
	
	Logger log = Logger.getLogger("GradeService.class");
	
	@Autowired
	private GradeRepository gp;
	
	@Autowired
	private TraineeClient tc;
	
	@Autowired
	private AssessmentService as;

	@Autowired
	private CategoryClient categoryClient;

	@Override
	public List<Grade> findAllGrades() {
		List<Grade> gradeList = gp.findAll();
		
		return checkTrainee(gradeList);
	}

	@Override
	public Grade findGradeById(int id) {
		Grade g = gp.findOne(id);
		if(g != null) contactTraineeService(g);
		
		return g;
	}

	@Override
	public Grade createGrade(GradeDTO grade) {
		Grade g = GradeConverter.convert(grade);
		log.debug("Creating Grade: " + g);
//		if(gp.findOne(g.getGradeId()) != null) return null;

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
	public boolean deleteGrade(GradeDTO grade) {
		Grade g = GradeConverter.convert(grade);
		log.debug("Deleting Grade: " + g);
		boolean exists = false;
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
			if(g.getTraineeId() >= 0) tc.findTraineeById(g.getTraineeId());
			
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with TraineeService");
			log.warn(e.getMessage());
			g.setTraineeId(-1);
			return false;
		}
	}

	@Override
	public List<Grade> findGradesByTraineeId(int id) {
		List<Grade> gradeList = gp.findGradesByTraineeId(id);
		
		return checkTrainee(gradeList);
	}

	@Override
	public List<Grade> findGradesByAssessmentId(int id) {
		List<Grade> gradeList = gp.findGradesByAssessmentId(id);
		
		return checkTrainee(gradeList);
	}
	
	@Override
	public List<Grade> findGradesByBatchIdAndWeekNumber(int id, int weekNumber) {
		List<Assessment> assessmentList = as.findAssessmentsByBatchIdAndWeekNumber(id, weekNumber);
		List<Integer> assessmentIds = new ArrayList<>();
		for(Assessment a : assessmentList) {
			assessmentIds.add(a.getAssessmentId());
		}
		List<Grade> gradeList = gp.findGradesByAssessmentIdIn(assessmentIds);
		
		return checkTrainee(gradeList);
	}
	
	@Override
	public List<Grade> findGradesByBatchId(int id) {
		List<Assessment> assessmentList = as.findAssessmentsByBatchId(id);
		List<Integer> assessmentIds = new ArrayList<>();
		for(Assessment a : assessmentList) {
			assessmentIds.add(a.getAssessmentId());
		}
		List<Grade> gradeList = gp.findGradesByAssessmentIdIn(assessmentIds);
		
		return checkTrainee(gradeList);
	}

	@Override
	public float findAvgAssessments(int id, int weekNum) {
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
		else return 0;


	}


  @Override
	public float findAverageAssessment(int id) {
		List<Grade> grades = this.findGradesByAssessmentId(id);
		float average = 0f;
		for(Grade g : grades) {
			average += g.getScore();
		}
		return (average/grades.size());
	}
  
  private List<Grade> checkTrainee(List<Grade> gradeList){
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < gradeList.size(); i++) {
			Grade g = gradeList.get(i);
			int tempTrainee = g.getTraineeId();
			
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

	@Override
	public Grade upsertGrade(GradeDTO g) {
		Grade grade = gp.findOne(g.getGradeId());
		if (grade != null) {
			grade.setScore(g.getScore());
			grade = gp.save(grade);
		} else {
			grade = gp.save(GradeConverter.convert(g));
		}
		return grade;
	}

	@Override
	public List<BatchGradeDto> getOverallGradeReportByBatchId(int batchId) {
		List<Assessment> assessments = this.as.findAssessmentsByBatchId(batchId);
		List<Grade> grades = new ArrayList<>();
		for (Assessment assessment : assessments) {
			grades.addAll(this.gp.findGradesByAssessmentId(assessment.getAssessmentId()));
		}
		List<Trainee> trainees = this.tc.getAllTraineesByBatchId(batchId);
		return GradeReportFactory.getOverallGradeReport(grades, trainees);
	}

	@Override
	public List<BatchGradeDto> getWeeklyGradeReportByBatchId(int batchId, int week) {
		List<Assessment> assessments = as.findAssessmentsByBatchIdAndWeekNumber(batchId, week);
		List<Grade> grades = new ArrayList<>();
		for (Assessment assessment : assessments) {
			grades.addAll(gp.findGradesByAssessmentId(assessment.getAssessmentId()));
		}
		List<Trainee> trainees = this.tc.getAllTraineesByBatchId(batchId);
		return GradeReportFactory.getOverallGradeReport(grades, trainees);
	}

	@Override
	public GradeComparisonDto compareTraineeToRestOfBatch(int traineeId) {
		Trainee trainee = tc.findTraineeById(traineeId);
		if (trainee != null) {
			List<Grade> grades = new ArrayList<>();
			List<Assessment> assessments = as.findAssessmentsByBatchId(trainee.getBatchId());
			assessments.forEach(assessment -> grades.addAll(gp.findGradesByAssessmentId(assessment.getAssessmentId())));
			return GradeReportFactory.getGradeComparisonReport(grades, assessments, traineeId);
		}
		return null;
	}

	@Override
	public GradeComparisonDto compareTraineeToRestOfBatchOnWeek(int traineeId, int week) {
		Trainee trainee = tc.findTraineeById(traineeId);
		if (trainee != null) {
			List<Grade> grades = new ArrayList<>();
			List<Assessment> assessments = as.findAssessmentsByBatchIdAndWeekNumber(trainee.getBatchId(), week);
			assessments.forEach(assessment -> grades.addAll(gp.findGradesByAssessmentId(assessment.getAssessmentId())));
			return GradeReportFactory.getGradeComparisonReport(grades, assessments, traineeId);
		}
		return null;
	}

	@Override
	public List<SpiderGraphDto> getSpiderGraphData(int batchId) {
		Stream<Assessment> assessments = this.as.findAssessmentsByBatchId(batchId).parallelStream();
		return assessments.map(assessment ->  {
			Category category = categoryClient.getCategoryById(assessment.getAssessmentCategory()).getBody();
			if (category != null && StringUtils.hasText(category.getSkillCategory())) {
				Stream<Grade> grades = this.gp.findGradesByAssessmentId(assessment.getAssessmentId()).parallelStream();
				return new SpiderGraphDto(-1, category.getSkillCategory(), grades.mapToDouble(Grade::getScore).summaryStatistics().getAverage(), assessment.getWeekNumber());
			} else {
				return new SpiderGraphDto();
			}
		}).collect(Collectors.toList());
	}

	@Override
	public GradeComparisonDto compareGradesOfBatchForWeek(int batchId, int week) {
		List<Assessment> assessments = as.findAssessmentsByBatchIdAndWeekNumber(batchId, week);
		List<Grade> grades = new ArrayList<>();
		assessments.forEach(assessment -> grades.addAll(gp.findGradesByAssessmentId(assessment.getAssessmentId())));
		return GradeReportFactory.getGradeComparisonReport(grades, assessments);
	}

	@Override
	public List<SpiderGraphDto> getSpiderGraphData(int batchId, int traineeId) {
		Stream<Assessment> assessments = this.as.findAssessmentsByBatchId(batchId).parallelStream();
		return assessments.flatMap(assessment ->  {
			Category category = categoryClient.getCategoryById(assessment.getAssessmentCategory()).getBody();
			if (category != null && StringUtils.hasText(category.getSkillCategory())) {
				List<Grade> allGrades = gp.findGradesByAssessmentId(assessment.getAssessmentId());
				Stream<SpiderGraphDto> traineeGrades = allGrades.parallelStream().filter(grade -> grade.getTraineeId() == traineeId).map(grade -> new SpiderGraphDto(grade.getTraineeId(), category.getSkillCategory(), grade.getScore(), assessment.getWeekNumber()));
				Stream<SpiderGraphDto> restOfBatchGrades = allGrades.parallelStream().filter(grade -> grade.getTraineeId() != traineeId).map(grade -> new SpiderGraphDto(-1, category.getSkillCategory(), grade.getScore(), assessment.getWeekNumber()));
				return Stream.concat(traineeGrades, restOfBatchGrades);
			} else {
				return Stream.empty();
			}
		}).collect(Collectors.toList());
	}

	@Override
	public List<MissingGrade> getMissingGradesForCurrentBatches(List<BatchEntity> batches) {
		List<MissingGrade> missingGrades = new ArrayList<>();
		for (BatchEntity batch : batches) {
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime startDate = LocalDateTime.ofInstant(batch.getStartDate().toInstant(), ZoneId.systemDefault());
			LocalDateTime endDate = LocalDateTime.ofInstant(batch.getEndDate().toInstant(), ZoneId.systemDefault());
			if (startDate.isBefore(today) && endDate.isAfter(today)) {
				if (batch.getWeeks() > 0) {
					List<Integer> missingWeeks = new ArrayList<>();
					for (int i = 1; i <= batch.getWeeks(); i++) {
						List<Grade> grades = this.findGradesByBatchIdAndWeekNumber(batch.getBatchId(), i);
						if (grades != null && grades.isEmpty()) {
							missingWeeks.add(i);
						}
					}
					if (!missingWeeks.isEmpty()) {
						missingGrades.add(new MissingGrade(batch.getBatchId(), batch.getTrainer(), batch.getLocation(), missingWeeks, batch.getSkillType()));
					}
				}
			}
		}

		return missingGrades;
	}
}
