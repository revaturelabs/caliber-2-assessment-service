package com.revature.caliber.intercoms.local;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.BatchEntity;

@Component
public class BatchClientLocalFallback implements BatchClientLocal {

	@Override
	public BatchEntity getBatchById(int batchId) {
		return null;
	}

}
