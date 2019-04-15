package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.beans.Category;
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
			
			if(!batchConnected.containsKey(a.getBatchId())) {
				if(contactBatchService(a)) {
					batchConnected.put(a.getBatchId(), true);
				} else {
					batchConnected.put(a.getBatchId(), false);
				}
			}
			if(!categoryConnected.containsKey(a.getAssessmentCategory())) {
				if(contactBatchService(a)) {
					categoryConnected.put(a.getAssessmentCategory(), true);
				} else {
					categoryConnected.put(a.getAssessmentCategory(), false);
				}
			}
			
			if(!batchConnected.get(a.getBatchId())) a.setBatchId(-1);
			if(!categoryConnected.get(a.getAssessmentCategory())) a.setAssessmentCategory(-1);
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
	public Assessment createAssessment(Assessment as) {
		if(ar.findOne(as.getAssessmentId()) != null) return null;
		return ar.save(as);
	}
	
	private boolean contactBatchService(Assessment as) {
		try {
			BatchEntity response = bc.getBatchById(as.getBatchId());
			if(response != null) {
				as.setBatchId(response.getBatchId());
			} else {
				as.setBatchId(-1);
			}
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with BatchService");
			log.warn(e.getMessage());
			as.setBatchId(-1);
			return false;
		}
	}
	
	private boolean contactCategoryService(Assessment as) {
		try {
			Category response = cc.getCategoryById(as.getAssessmentCategory()).getBody();
			if(response != null) {
				as.setAssessmentCategory(response.getCategoryId());
			} else {
				as.setAssessmentCategory(-1);
			}
			return true;
		} catch(Exception e) {
			log.warn("Could not connect with CategoryService");
			log.warn(e.getMessage());
			as.setAssessmentCategory(-1);
			return false;
		}
	}
}
