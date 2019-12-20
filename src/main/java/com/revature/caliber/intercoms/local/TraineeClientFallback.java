package com.revature.caliber.intercoms.local;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Trainee;

import java.util.Collections;
import java.util.List;

@Component
public class TraineeClientFallback implements TraineeClientLocal {

	@Override
	public Trainee findTraineeById(int id) {
		return null;
	}

	@Override
	public List<Trainee> getAllTraineesByBatchId(int batchId) {
		return null;
	}
}
