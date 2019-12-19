package com.revature.caliber.intercoms.local;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Category;

@Component
public class CategoryClientLocalFallback implements CategoryClientLocal {

	@Override
	public ResponseEntity<Category> getCategoryById(int categoryId) {
		return null;
	}

}
