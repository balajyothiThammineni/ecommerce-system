package com.springboot.ecommerce.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int mCategoryId;
	@Column(nullable = false)
	private String mCategoryName;
	
	@ManyToOne
	private Executive executive;

	public int getmCategoryId() {
		return mCategoryId;
	}
	public void setmCategoryId(int mCategoryId) {
		this.mCategoryId = mCategoryId;
	}
	public String getmCategoryName() {
		return mCategoryName;
	}
	public void setmCategoryName(String mCategoryName) {
		this.mCategoryName = mCategoryName;
	}
	public void setExecutive(Executive executive) {
		this.executive= executive;
		
	}

}
