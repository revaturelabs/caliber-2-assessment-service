package com.revature.caliber.intercoms.perf;

import com.revature.caliber.intercoms.base.BatchClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 */
@Profile("perf")
@FeignClient(url = "${intercom.service.batch}")
public interface BatchClientPerf extends BatchClient  {
}
