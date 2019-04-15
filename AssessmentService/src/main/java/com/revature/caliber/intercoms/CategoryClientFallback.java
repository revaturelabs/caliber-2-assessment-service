package com.revature.caliber.intercoms;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Category;

@Component
public class CategoryClientFallback implements CategoryClient {

	@Override
	public ResponseEntity<Category> getCategoryById(Integer categoryId) {
		return null;
	}

}
