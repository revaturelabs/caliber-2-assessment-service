package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="category", url="localhost:9090/")
public interface CategoryClient {
	
	//TODO: ask TEAM3 about this boi; change Integer to Category bean
	@RequestMapping(method = RequestMethod.GET, value="all/category/{id}")
	public ResponseEntity <Integer> getCategoryById(@PathVariable(value="id") Integer categoryId);
}
