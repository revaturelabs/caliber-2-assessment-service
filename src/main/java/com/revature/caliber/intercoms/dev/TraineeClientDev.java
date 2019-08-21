package com.revature.caliber.intercoms.dev;

import com.revature.caliber.intercoms.base.TraineeClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 * 8/21/2019
 */
@Profile("dev && !prod")
@FeignClient(url = "http://user.caliber-2-dev")
public interface TraineeClientDev extends TraineeClient {
}
