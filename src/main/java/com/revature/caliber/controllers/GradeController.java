package com.revature.caliber.controllers;

import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.MissingGrade;
import com.revature.caliber.dto.BatchGradeDto;
import com.revature.caliber.dto.GradeComparisonDto;
import com.revature.caliber.dto.GradeDTO;
import com.revature.caliber.dto.SpiderGraphDto;
import com.revature.caliber.services.GradeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class GradeController {
    
    private Logger log = Logger.getLogger("GradeController.class");
    
    @Autowired
    private GradeService gs;
    
    @GetMapping("/all/grade/all")
    public ResponseEntity<List<Grade>> findAllGrades(){
        log.debug("Inside getAllGrades");
        List<Grade> temp =  gs.findAllGrades();
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/trainee/{id}")
    public ResponseEntity<List<Grade>> findGradesByTrainee(@PathVariable("id") Integer id){
        log.debug("Inside findGradesByTrainee");
        List<Grade> temp =  gs.findGradesByTraineeId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/assessment/{id}")
    public ResponseEntity<List<Grade>> findGradesByAssessment(@PathVariable("id") Integer id){
        log.debug("Inside findGradesByAssessment");
        List<Grade> temp =  gs.findGradesByAssessmentId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @GetMapping("/all/grade/batch/{id}")
    public ResponseEntity<List<Grade>> findGradesByBatch(@PathVariable("id") Integer id, @RequestParam(name="week", required=false) Integer weekNumber){
        log.debug("Inside findGradesByBatch");
        List<Grade> temp =  null;
        
        if(weekNumber != null) temp = gs.findGradesByBatchIdAndWeekNumber(id, weekNumber);
        else temp = gs.findGradesByBatchId(id);
        
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/grade/{id}")
    public ResponseEntity<Grade> findGradeById(@PathVariable("id") Integer id){
        log.debug("Inside findGradeById");
        Grade temp = gs.findGradeById(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @PostMapping(value="/all/grade/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Grade> createGrade(@RequestBody GradeDTO grade) {
    	Grade temp = gs.createGrade(grade);
    	return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }
  
    @PutMapping(value="all/grade/update", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody GradeDTO grade) {
      log.debug("Updating Grade: " + grade);
      Grade temp = gs.updateGrade(grade);
      if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @DeleteMapping(value="all/grade/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Boolean> deleteLocation(@Valid @RequestBody GradeDTO grade) {
      Boolean temp = gs.deleteGrade(grade);
      if(!temp) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    
    @GetMapping("/all/grade/average")
    public ResponseEntity<Float> findAverageGradeByWeek(@RequestParam(name="assessment", required=false) Integer assessmentId, @RequestParam(name="batch", required=false) Integer batchId, @RequestParam(name="week", required=false) Integer weekNum){
    	if(assessmentId != null && batchId == null && weekNum == null) {
            log.debug("Inside findAverageGradeByAssessment");
            Float temp =  gs.findAverageAssessment(assessmentId);
            if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        } else if (assessmentId == null && batchId != null && weekNum != null) {
        	log.debug("Inside findAverageGradeByWeek");
            Float temp =  gs.findAvgAssessments(batchId, weekNum);
            if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        } else  {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	
    }
    
    /**
     * 
     * @param activeBatches
     * @return Response entity with JSON description of batches and weeks that are missing grades
     */
    @PostMapping(value="/all/grade/missingGrades", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MissingGrade>> retrieveMissingGradesFromBatches(@Valid @RequestBody List<BatchEntity> activeBatches) {
    	List<MissingGrade> missingGrades = gs.getMissingGradesForCurrentBatches(activeBatches);
    	if (missingGrades != null && !missingGrades.isEmpty()) {
    		return ResponseEntity.ok(missingGrades);
	    }
    	return ResponseEntity.noContent().build();
    }

    @PutMapping("/grade")
		public ResponseEntity<Grade> upsertGrade(@RequestBody GradeDTO gradeDto) {
    	Grade grade = gs.upsertGrade(gradeDto);
    	return ResponseEntity.ok(grade);
    }

    @GetMapping("/grade/reports/{batchId}/overall")
		public ResponseEntity<List<BatchGradeDto>> getGradeReportForBatch(@PathVariable("batchId") int batchId) {
    	List<BatchGradeDto> grades = gs.getOverallGradeReportByBatchId(batchId);
    	if (grades != null) {
    		return ResponseEntity.ok(grades);
	    }
    	return ResponseEntity.noContent().build();
    }

	@GetMapping("/grade/reports/{batchId}/spider")
	public ResponseEntity<List<SpiderGraphDto>> getSpiderGraphDataForBatch(@PathVariable("batchId") int batchId) {
		List<SpiderGraphDto> data = this.gs.getSpiderGraphData(batchId);
		if (data != null && !data.isEmpty()) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/grade/reports/{batchId}/spider/{traineeId}")
	public ResponseEntity<List<SpiderGraphDto>> getSpiderGraphDataForTraineeAndBatch(@PathVariable("batchId") int batchId, @PathVariable("traineeId") int traineeId) {
    	List<SpiderGraphDto> data = this.gs.getSpiderGraphData(batchId, traineeId);
    	if (data != null && !data.isEmpty()) {
    		return ResponseEntity.ok(data);
	    }
    	return ResponseEntity.noContent().build();
	}

    @GetMapping("/grade/reports/{batchId}/overall/{week}")
		public ResponseEntity<List<BatchGradeDto>> getWeeklyGradeReportForBatch(@PathVariable("batchId") int batchId, @PathVariable("week") int week) {
			List<BatchGradeDto> grades = gs.getWeeklyGradeReportByBatchId(batchId, week);
			if (grades != null) {
				return ResponseEntity.ok(grades);
			}
	    return ResponseEntity.noContent().build();
    }

    @GetMapping("/grade/reports/individual/{traineeId}")
		public ResponseEntity<GradeComparisonDto> getTraineeGradesComparedToBatch(@PathVariable(name = "traineeId") int traineeId) {
      GradeComparisonDto gradeComparisonDto = gs.compareTraineeToRestOfBatch(traineeId);
      if (gradeComparisonDto != null) {
        return ResponseEntity.ok(gradeComparisonDto);
	    }
    	return ResponseEntity.noContent().build();
    }

	@GetMapping("/grade/reports/individual/{traineeId}/{week}")
	public ResponseEntity<GradeComparisonDto> getTraineeGradesComparedToBatchOnWeek(@PathVariable(name = "traineeId") int traineeId, @PathVariable("week") int week) {
		GradeComparisonDto gradeComparisonDto = gs.compareTraineeToRestOfBatchOnWeek(traineeId, week);
		if (gradeComparisonDto != null) {
			return ResponseEntity.ok(gradeComparisonDto);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/grade/reports/batch/{batchId}/{week}")
	public ResponseEntity<GradeComparisonDto> getGradeComparisonForBatchOnWeek(@PathVariable("batchId") int batchId, @PathVariable("week") int week) {
    	GradeComparisonDto dto = gs.compareGradesOfBatchForWeek(batchId, week);
    	if (dto != null) {
    		return ResponseEntity.ok(dto);
	    }
    	return ResponseEntity.noContent().build();
	}
}
