package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.intercoms.BatchClient;
import com.revature.caliber.intercoms.CategoryClient;
import com.revature.caliber.repositories.AssessmentRepository;

@Service
public class AssessmentService implements AssessmentServiceInterface{
	
	Logger log = Logger.getLogger("AssessmentService.class");
	
	@Autowired
	private AssessmentRepository ar;
	
	@Autowired
	private BatchClient bc;
		
	@Autowired
	private CategoryClient cc;

	@Override
	public List<Assessment> findAllAssessments() {
		List<Assessment> AssessmentList = ar.findAll();
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		Map<Integer, Boolean> categoryConnected = new HashMap<>();
		
		for(int i = 0; i < AssessmentList.size(); i++) {
			Assessment a = AssessmentList.get(i);
			Integer tempBatch = a.getBatchId();
			Integer tempCategory = a.getAssessmentCategory();
			
			if(!batchConnected.containsKey(a.getBatchId())) {
				
				if(contactBatchService(a)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			if(!categoryConnected.containsKey(a.getAssessmentCategory())) {
				
				if(contactBatchService(a)) {
					categoryConnected.put(tempCategory, true);
				} else {
					categoryConnected.put(tempCategory, false);
				}
			}
			
			if(!batchConnected.get(tempBatch)) a.setBatchId(-1);
			if(!categoryConnected.get(tempCategory)) a.setAssessmentCategory(-1);
		}
		
		return AssessmentList;
	}
 
	@Override
	public Assessment findAssessmentById(Integer id) {
		Assessment as = ar.findOne(id);
		if(as != null) {
			contactBatchService(as);
			contactCategoryService(as);
		}
		
		return as;
	}
	
	@Override
	public Boolean deleteAssessment(Assessment as) {
		Boolean exists = false;
		if(ar.findOne(as.getAssessmentId()) != null) exists = true;
		if(exists) {
			ar.delete(as);
			return true;
		} else {
			return false;
		}
	}
	
	public Assessment createAssessment(Assessment as) {
		if(ar.findOne(as.getAssessmentId()) != null) return null;
		return ar.save(as);
	}
  
	@Override
	public Assessment updateAssessment(Assessment as) {
		log.debug("Updating Assessment: " + as);
		if(ar.findOne(as.getAssessmentId()) == null) return null;
		return ar.save(as);
	}
	
	private boolean contactBatchService(Assessment as) {
		try {
			if(as.getBatchId() != null) bc.getBatchById(as.getBatchId());
			
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with BatchService");
			log.warn(e.getMessage());
			as.setBatchId(-1);
			return false;
		}
	}

	@Override
	public List<Assessment> findAssessmentsByBatchId(Integer batchId) {
		List<Assessment> AssessmentList = ar.findAssessmentsByBatchId(batchId);
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		Map<Integer, Boolean> categoryConnected = new HashMap<>();
		
		for(int i = 0; i < AssessmentList.size(); i++) {
			Assessment a = AssessmentList.get(i);
			Integer tempBatch = a.getBatchId();
			Integer tempCategory = a.getAssessmentCategory();
			
			if(!batchConnected.containsKey(a.getBatchId())) {
				
				if(contactBatchService(a)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			if(!categoryConnected.containsKey(a.getAssessmentCategory())) {
				
				if(contactBatchService(a)) {
					categoryConnected.put(tempCategory, true);
				} else {
					categoryConnected.put(tempCategory, false);
				}
			}
			
			if(!batchConnected.get(tempBatch)) a.setBatchId(-1);
			if(!categoryConnected.get(tempCategory)) a.setAssessmentCategory(-1);
		}
		
		return AssessmentList;
	}

	@Override
	public List<Assessment> findAssessmentsByCategory(Integer categoryId) {
		List<Assessment> AssessmentList = ar.findAssessmentsByAssessmentCategory(categoryId);
		Map<Integer, Boolean> batchConnected = new HashMap<>();
		Map<Integer, Boolean> categoryConnected = new HashMap<>();
		
		for(int i = 0; i < AssessmentList.size(); i++) {
			Assessment a = AssessmentList.get(i);
			Integer tempBatch = a.getBatchId();
			Integer tempCategory = a.getAssessmentCategory();
			
			if(!batchConnected.containsKey(a.getBatchId())) {
				
				if(contactBatchService(a)) {
					batchConnected.put(tempBatch, true);
				} else {
					batchConnected.put(tempBatch, false);
				}
			}
			if(!categoryConnected.containsKey(a.getAssessmentCategory())) {
				
				if(contactBatchService(a)) {
					categoryConnected.put(tempCategory, true);
				} else {
					categoryConnected.put(tempCategory, false);
				}
			}
			
			if(!batchConnected.get(tempBatch)) a.setBatchId(-1);
			if(!categoryConnected.get(tempCategory)) a.setAssessmentCategory(-1);
		}
	
		return AssessmentList;
	}


	@Override
	public List<Assessment> findAssessmentsByBatchIdAndWeekNumber(Integer id, Integer weekNumber) {
		List<Assessment> AssessmentList = ar.findAssessmentsByBatchIdAndWeekNumber(id, weekNumber);
		Map<Integer, Boolean> alreadyConnected = new HashMap<>();
		
		for(int i = 0; i < AssessmentList.size(); i++) {
			Assessment a = AssessmentList.get(i);
			
			if(!alreadyConnected.containsKey(a.getBatchId())) {
				if(contactBatchService(a)) {
					alreadyConnected.put(a.getBatchId(), true);
				} else {
					alreadyConnected.put(a.getBatchId(), false);
				}
			}
			
			if(!alreadyConnected.get(a.getBatchId())) {
				a.setBatchId(-1);
			}
		}
		System.out.println(AssessmentList);
		return AssessmentList;
	}

	

	private boolean contactCategoryService(Assessment as) {
		try {
			if(as.getAssessmentCategory() != null) cc.getCategoryById(as.getAssessmentCategory()).getBody();
			
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with CategoryService");
			log.warn(e.getMessage());
			as.setAssessmentCategory(-1);
			return false;
		}
	}


}
