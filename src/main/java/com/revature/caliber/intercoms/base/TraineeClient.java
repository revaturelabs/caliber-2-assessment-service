package com.revature.caliber.intercoms.base;

import com.revature.caliber.beans.Trainee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author William Gentry
 * 8/21/2019
 */
public interface TraineeClient {

  @GetMapping(value="/user/all/trainee/{id}")
  public Trainee findTraineeById(@PathVariable("id") Integer id);

  @GetMapping(value="/user/all/trainee")
  public List<Trainee> getAllTraineesByBatchId(@RequestParam("batch") Integer batch);
}
