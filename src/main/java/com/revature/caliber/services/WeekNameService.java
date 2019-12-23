package com.revature.caliber.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.WeekName;
import com.revature.caliber.repositories.WeekNameRepository;

@Service
public class WeekNameService {

	@Autowired
	private WeekNameRepository repo;
	
	public List<WeekName> findAll() {
		return repo.findAll();
	}
	
	public int updateName(WeekName entity) {
		return repo.updateName(entity.getName(), entity.getId());
	}
	
	public WeekName findByBatchIdAndWeekNumber(int batchId, int weekNumber) {
		return repo.findByBatchIdAndWeekNumber(batchId, weekNumber);
	}
	
	public List<WeekName> findByBatchId(int batchId) {
		return repo.findByBatchId(batchId);
	}
	
	public WeekName findById(int id) {
		return repo.findOne(id);
	}
	
	public WeekName upsertWeekName(WeekName weekName) {
		WeekName entity = repo.findOne(weekName.getId());
		
		if(entity == null) {
			entity = repo.save(weekName);
		}
		
		entity.setName(weekName.getName());
		entity = repo.save(entity);
		
		return entity;
	}
	
}
