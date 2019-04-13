package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.BatchEntity;
import com.revature.caliber.intercoms.BatchClient;
import com.revature.caliber.repositories.AssessmentRepository;

@Service
public class AssessmentService implements AssessmentServiceInterface{
	
	Logger log = Logger.getLogger("AssessmentService.class");
	
	@Autowired
	private AssessmentRepository ar;
	
	@Autowired
	private BatchClient bc;

	@Override
	public List<Assessment> findAllAssessments() {
		List<Assessment> AssessmentList = ar.findAll();
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
		
		return AssessmentList;
	}
 
	@Override
	public Assessment findAssessmentById(Integer id) {
		Assessment as = ar.findOne(id);
		if(as != null) contactBatchService(as);
		
		return as;
	}
	
	@Override
	public Assessment updateAssessment(Assessment as) {
		log.debug("Updating Assessment: " + as);
		return ar.save(as);
    
  @Override
	public void deleteAssessment(Assessment as) {
		ar.delete(as);
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
	
	//TODO: create contactCategoryService method
}
