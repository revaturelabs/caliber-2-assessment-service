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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((categoryOwner == null) ? 0 : categoryOwner.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((skillCategory == null) ? 0 : skillCategory.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryOwner == null) {
			if (other.categoryOwner != null)
				return false;
		} else if (!categoryOwner.equals(other.categoryOwner))
			return false;
		if (isActive != other.isActive)
			return false;
		if (skillCategory == null) {
			if (other.skillCategory != null)
				return false;
		} else if (!skillCategory.equals(other.skillCategory))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", skillCategory=" + skillCategory + ", isActive=" + isActive
				+ ", categoryOwner=" + categoryOwner + "]";
	}
	
	
	
}
