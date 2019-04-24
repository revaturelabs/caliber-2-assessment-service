package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Trainee;


@FeignClient(name="zuul", fallback=TraineeClientFallback.class)
public interface TraineeClient {
	@GetMapping(value="all/trainee/{id}")
	public Trainee findTraineeById(@PathVariable("id") Integer id);
	
}
