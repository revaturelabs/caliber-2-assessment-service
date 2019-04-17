package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.BatchEntity;

@FeignClient(name = "zuul", fallback=BatchClientFallback.class)
public interface BatchClient {
	
	@RequestMapping(method = RequestMethod.GET, value="all/batch/{id}")
	public BatchEntity getBatchById(@PathVariable(value="id") Integer batchId);
}
