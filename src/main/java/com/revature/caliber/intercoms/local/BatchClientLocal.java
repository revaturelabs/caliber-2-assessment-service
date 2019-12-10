package com.revature.caliber.intercoms.local;

import com.revature.caliber.intercoms.base.BatchClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.BatchEntity;

@Profile("local")
@FeignClient(name = "batch-service", fallback= BatchClientLocalFallback.class)
public interface BatchClientLocal extends BatchClient {

}
