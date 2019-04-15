package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Trainee;


//TODO: No way to get trainee by id in UserService?
@FeignClient(name="trainee", url="localhost:9090/",fallback=TraineeClientFallback.class)
public interface TraineeClient {
	@RequestMapping(method = RequestMethod.GET, value="all/trainee/{id}")
	public Trainee findTraineeById(@PathVariable("id") Integer id);
	
}
