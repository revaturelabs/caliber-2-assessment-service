package com.revature.caliber.intercoms;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.BatchEntity;

@Component
public class BatchClientFallback implements BatchClient {

	@Override
	public BatchEntity getBatchById(Integer batchId) {
		return null;
	}

}
