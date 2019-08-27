package com.revature.caliber.intercoms.base;

import com.revature.caliber.beans.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author William Gentry
 * 8/21/2019
 */
public interface CategoryClient {

  @GetMapping(value="/category/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable(value="id") Integer categoryId);
}
