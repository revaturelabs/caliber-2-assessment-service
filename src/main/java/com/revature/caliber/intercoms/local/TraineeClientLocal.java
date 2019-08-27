package com.revature.caliber.intercoms.local;

import com.revature.caliber.intercoms.base.TraineeClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Trainee;

@Profile("!dev && !prod")
@FeignClient(name="user-service", fallback=TraineeClientFallback.class)
public interface TraineeClientLocal extends TraineeClient {
	
}
