package com.revature.caliber.intercoms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Category;

@FeignClient(name="category-service", fallback=CategoryClientFallback.class)
public interface CategoryClient {
	
	@GetMapping(value="/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value="id") Integer categoryId);
}
