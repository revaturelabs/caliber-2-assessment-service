package com.revature.caliber.intercoms.perf;

import com.revature.caliber.intercoms.base.TraineeClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 */
@Profile("perf")
@FeignClient(name = "trainee", url = "${client.url}")
public interface TraineeClientPerf extends TraineeClient {
}
