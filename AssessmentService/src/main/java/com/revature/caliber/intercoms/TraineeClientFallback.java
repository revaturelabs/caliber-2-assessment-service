package com.revature.caliber.intercoms;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Trainee;

@Component
public class TraineeClientFallback implements TraineeClient {

	@Override
	public Trainee findTraineeById(Integer id) {
		return null;
	}

}
