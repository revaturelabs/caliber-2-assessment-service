package com.revature.caliber.intercoms.local;

import com.revature.caliber.intercoms.base.CategoryClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Category;

@Profile("!dev && !prod")
@FeignClient(name="category-service", fallback= CategoryClientLocalFallback.class)
public interface CategoryClientLocal extends CategoryClient {
}
