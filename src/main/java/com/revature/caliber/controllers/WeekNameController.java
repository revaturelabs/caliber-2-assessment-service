package com.revature.caliber.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.WeekName;
import com.revature.caliber.services.WeekNameService;

@RestController
@RequestMapping("/weekName")
@CrossOrigin(value = "*")
public class WeekNameController {

	@Autowired
	private WeekNameService weekNameService;
	
	@GetMapping("/{id}")
	public ResponseEntity<WeekName> getWeekName(@PathVariable("id") int id) {
		WeekName weekName = weekNameService.findById(id);
		
		if(weekName == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(weekName);
	}
	
	@GetMapping("/batch/{id}")
	public ResponseEntity<List<WeekName>> getWeekNameByBatchId(@PathVariable("id") int id) {
		List<WeekName> names = weekNameService.findByBatchId(id);
		
		if(names == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(names);
	}
	
	@PutMapping("/update")
	public ResponseEntity<WeekName> updateName(@RequestBody WeekName weekName) {
		WeekName entity = weekNameService.upsertWeekName(weekName);
		
		if(entity == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(entity);
	}
}