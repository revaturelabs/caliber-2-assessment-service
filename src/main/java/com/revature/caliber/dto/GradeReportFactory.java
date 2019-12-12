package com.revature.caliber.dto;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author William Gentry
 */
public class GradeReportFactory {

	// Restrict instantiation
	private GradeReportFactory() {}

	public static List<BatchGradeDto> getOverallGradeReport(List<Grade> grades, List<Trainee> trainees) {
		final List<BatchGradeDto> overallTraineeGrades = new ArrayList<>();
		if (trainees != null && grades != null) {
			trainees.forEach(trainee -> {
				Stream<Grade> gradeStream = grades.parallelStream().filter(grade -> grade.getTraineeId() == trainee.getTraineeId());
				double average = gradeStream.mapToDouble(Grade::getScore).summaryStatistics().getAverage();
				overallTraineeGrades.add(new BatchGradeDto(trainee.getName(), average));
			});
		}
		return overallTraineeGrades.parallelStream().sorted(Comparator.comparing(BatchGradeDto::getAverage).reversed()).collect(Collectors.toList());
	}

	public static GradeComparisonDto getGradeComparisonReport(List<Grade> grades, List<Assessment> assessments, int traineeId) {
		final GradeComparisonDto gradeComparisonDto = new GradeComparisonDto();
		if (grades != null && assessments != null) {
			for (Assessment assessment : assessments) {
				Stream<Grade> traineeGrades = grades.parallelStream().filter(grade -> grade.getTraineeId() == traineeId && grade.getAssessmentId() == assessment.getAssessmentId());
				gradeComparisonDto.addTraineeGrade(assessment.getAssessmentType(), traineeGrades.mapToDouble(Grade::getScore).summaryStatistics().getAverage());
				Stream<Grade> batchGrades = grades.parallelStream().filter(grade -> grade.getTraineeId() != traineeId && grade.getAssessmentId() == assessment.getAssessmentId());
				gradeComparisonDto.addRestOfBatchGrades(assessment.getAssessmentType(), batchGrades.mapToDouble(Grade::getScore).summaryStatistics().getAverage());
			}
		}
		return gradeComparisonDto;
	}

	public static GradeComparisonDto getGradeComparisonReport(List<Grade> grades, List<Assessment> assessments) {
		final GradeComparisonDto gradeComparisonDto = new GradeComparisonDto();
		if (grades != null && assessments != null) {
			for (Assessment assessment : assessments) {
				Stream<Grade> batchGrades = grades.parallelStream().filter(grade -> grade.getAssessmentId() == assessment.getAssessmentId());
				gradeComparisonDto.addRestOfBatchGrades(assessment.getAssessmentType(), batchGrades.mapToDouble(Grade::getScore).summaryStatistics().getAverage());
			}
		}
		return gradeComparisonDto;
	}
}
