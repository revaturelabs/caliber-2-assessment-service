package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.NATURAL)
public enum TraineeFlag {

	/**
	 * Trainee is not flagged
	 */
	@JsonProperty("NONE")
	NONE,
	
	/**
	 * Trainee should be evaluated for termination 
	 */
	@JsonProperty("RED")
	RED,
	
	/**
	 * Trainee has 'trainer potential'
	 */
	@JsonProperty("GREEN")
	GREEN,
	
	/**
	 * Trainee has been promoted to trainer
	 */
	@JsonProperty("TRAINER")
	TRAINER
	
}