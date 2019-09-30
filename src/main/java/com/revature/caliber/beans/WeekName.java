package com.revature.caliber.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Length;

@Entity
public class WeekName implements Serializable {

	private static final long serialVersionUID = -7660857733808966353L;

	@Id
	@Column(name = "WEEK_NAME_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "WEEK_NAME_ID_SEQUENCE")
	@SequenceGenerator(name = "WEEK_NAME_ID_SEQUENCE", sequenceName = "WEEK_NAME_ID_SEQUENCE")
	private int id;

	@Column(name = "BATCH_ID", nullable = false)
	private int batchId;
	
	@Column(name = "WEEK_NUMBER", nullable = false)
	private int weekNumber;
	
	@Length(min=1, max=40)
	@Column(name = "WEEK_NAME", nullable = false)
	private String name;

	public WeekName() {
		super();
	}

	public WeekName(int id, int batchId, int weekNumber, String name) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.weekNumber = weekNumber;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + weekNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "WeekName [id=" + id + ", batchId=" + batchId + ", weekNumber=" + weekNumber + ", name=" + name + "]";
	}
}
