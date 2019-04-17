package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Category;

@FeignClient(name="zuul", fallback=CategoryClientFallback.class)
public interface CategoryClient {
	
	@RequestMapping(method = RequestMethod.GET, value="all/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value="id") Integer categoryId);
}
