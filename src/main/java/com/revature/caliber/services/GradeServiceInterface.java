package com.revature.caliber.services;

import java.util.List;
import java.util.Set;

import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.MissingGrade;
import com.revature.caliber.dto.BatchGradeDto;
import com.revature.caliber.dto.GradeComparisonDto;
import com.revature.caliber.dto.SpiderGraphDto;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.dto.GradeDTO;

@Service
public interface GradeServiceInterface {
	public List<Grade> findAllGrades();
	public Grade findGradeById(int id);
	public Grade createGrade(GradeDTO g);
	public Grade updateGrade(GradeDTO g);
	public boolean deleteGrade(GradeDTO g);
	public List<Grade> findGradesByTraineeId(int id);
	public List<Grade> findGradesByBatchId(int id);
	public List<Grade> findGradesByAssessmentId(int id);
	public List<Grade> findGradesByBatchIdAndWeekNumber(int id, int weekNumber);
	public float findAvgAssessments(int id, int weekNum);
	public float findAverageAssessment(int id);
	Grade upsertGrade(GradeDTO g);
	List<BatchGradeDto> getOverallGradeReportByBatchId(int batchId);
	List<BatchGradeDto> getWeeklyGradeReportByBatchId(int batchId, int week);
	GradeComparisonDto compareTraineeToRestOfBatch(int traineeId);
	GradeComparisonDto compareTraineeToRestOfBatchOnWeek(int traineeId, int week);
	GradeComparisonDto compareGradesOfBatchForWeek(int batchId, int week);
	List<SpiderGraphDto> getSpiderGraphData(int batchId);
	List<SpiderGraphDto> getSpiderGraphData(int batchId, int traineeId);
	List<MissingGrade> getMissingGradesForCurrentBatches(List<BatchEntity> batches);
}
