package com.revature.caliber.intercoms;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.caliber.beans.Trainee;


//TODO: No way to get trainee by id in UserService?
@FeignClient(name="trainee", url="localhost:9090/")
public interface TraineeClient {
	@RequestMapping(method = RequestMethod.GET, value="all/trainee/{id}")
	public Trainee findTraineeById(@PathVariable("id") Integer id);
	
	@GetMapping(value = "all/trainee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainee>> findAllByBatch(@RequestParam(required = true) Integer batch);
}
