package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Trainee;


@FeignClient(name="zuul", fallback=TraineeClientFallback.class)
public interface TraineeClient {
	@RequestMapping(method = RequestMethod.GET, value="all/trainee/{id}")
	public Trainee findTraineeById(@PathVariable("id") Integer id);
	
}
