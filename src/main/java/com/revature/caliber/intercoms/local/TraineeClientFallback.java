package com.revature.caliber.intercoms.local;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Trainee;

@Component
public class TraineeClientFallback implements TraineeClientLocal {

	@Override
	public Trainee findTraineeById(Integer id) {
		return null;
	}

}
