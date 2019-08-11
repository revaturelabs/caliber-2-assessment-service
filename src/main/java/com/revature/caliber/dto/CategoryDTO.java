package com.revature.caliber.dto;

public class CategoryDTO {

	private int categoryId;
	
	private String skillCategory;
	
	private boolean isActive;
	
	private String categoryOwner;
	
	public CategoryDTO() {
		super();
	}

	public CategoryDTO(int categoryId, String skillCategory, boolean isActive, String categoryOwner) {
		super();
		this.categoryId = categoryId;
		this.skillCategory = skillCategory;
		this.isActive = isActive;
		this.categoryOwner = categoryOwner;
	}



	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCategoryOwner() {
		return categoryOwner;
	}

	public void setCategoryOwner(String categoryOwner) {
		this.categoryOwner = categoryOwner;
	}

	
	
}
