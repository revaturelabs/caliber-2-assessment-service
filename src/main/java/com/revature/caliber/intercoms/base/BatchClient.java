package com.revature.caliber.intercoms.base;

import com.revature.caliber.beans.BatchEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author William Gentry
 * 8/21/2019
 */
public interface BatchClient {

  @GetMapping(value="batch/all/batch/{id}")
  public BatchEntity getBatchById(@PathVariable(value="id") Integer batchId);
}
