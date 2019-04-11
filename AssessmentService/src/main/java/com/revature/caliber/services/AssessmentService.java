package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		List<Assessment> asList = ar.findAll();
		for(int i = 0; i < asList.size(); i++) {
			Assessment as = asList.get(i);
			if(!contactBatchService(as)) {
				for(int j = i; j < asList.size(); j++) {
					asList.get(j).setBatchId(-1);
				}
				break;
			}
		}
		return asList;
	}
 
	@Override
	public Assessment findAssessmentById(Integer id) {
		Assessment as = ar.findOne(id);
		contactBatchService(as);
		
		return as;
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
		} catch(NullPointerException npe) {
			log.warn("Batch id does not exist");
			log.warn(npe.getMessage());
			as.setBatchId(-1);
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
