package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.BatchEntity;

@FeignClient(name = "batch-service", fallback=BatchClientFallback.class)
public interface BatchClient {
	
	@GetMapping(value="all/batch/{id}")
	public BatchEntity getBatchById(@PathVariable(value="id") Integer batchId);
}
