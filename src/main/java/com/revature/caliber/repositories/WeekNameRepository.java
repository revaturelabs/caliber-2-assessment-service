package com.revature.caliber.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.WeekName;

@Repository
public interface WeekNameRepository extends JpaRepository<WeekName, Integer> {
	
	/**
	 * 
	 * @param name
	 * @param id
	 * @return 1 if success
	 */
	@Modifying
	@Query("UPDATE WeekName w SET w.name = :weekName WHERE w.id = :weekNameId")
	public int updateName(@Param("weekName") String name, @Param("weekNameId") int id);

	@Query("SELECT w FROM WeekName w WHERE w.batchId = :batchId AND w.weekNumber = :weekNumber")
	public WeekName findByBatchIdAndWeekNumber(@Param("batchId") int batchId, @Param("weekNumber") int weekNumber);
	
	@Query("SELECT w FROM WeekName w WHERE w.batchId = :batchId")
	public List<WeekName> findByBatchId(@Param("batchId") int batchId);
}
