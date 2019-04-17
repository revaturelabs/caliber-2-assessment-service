package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.NATURAL)
public enum TrainingStatus implements Serializable{
	
	/**
	 * Trainee is Signed (has signed the contract)
	 */
	@JsonProperty("Signed")
	SIGNED,
	
	/**
	 * Trainee is Selected for training
	 */
	@JsonProperty("Selected")
	SELECTED,
	
	/**
	 * Trainee is currently in training
	 */
	@JsonProperty("Training")
	TRAINING,
	
	/**
	 * Trainee is marketing
	 */
	@JsonProperty("Marketing")
	MARKETING,
	
	/**
	 * Trainee is confirmed for training
	 */
	@JsonProperty("Confirmed")
	CONFIRMED,
	
	/**
	 * Trainee is employed
	 */
	@JsonProperty("Employed")
	EMPLOYED,
	
	/**
	 * Trainee has been dropped
	 */
	@JsonProperty("Dropped")
	DROPPED,
	
	/**
	 * Trainee is currently on a project
	 */
	@JsonProperty("Project")
	PROJECT,
	
	/**
	 * Trainee is currently in staging
	 */
	@JsonProperty("Staging")
	STAGING
}