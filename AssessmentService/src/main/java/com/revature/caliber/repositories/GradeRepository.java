package com.revature.caliber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
	

}
