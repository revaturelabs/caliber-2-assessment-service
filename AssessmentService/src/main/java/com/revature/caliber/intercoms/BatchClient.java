package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.BatchEntity;

@FeignClient(name="category", url="localhost:9090/")
public interface BatchClient {
	
	@RequestMapping(method = RequestMethod.GET, value="all/batch/{id}")
	public ResponseEntity <BatchEntity> getBatchById(@PathVariable(value="id") Integer batchId);
}
