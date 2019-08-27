package com.revature.caliber.intercoms.base;

import com.revature.caliber.beans.Trainee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author William Gentry
 * 8/21/2019
 */
public interface TraineeClient {

  @GetMapping(value="user/all/trainee/{id}")
  public Trainee findTraineeById(@PathVariable("id") Integer id);
}
