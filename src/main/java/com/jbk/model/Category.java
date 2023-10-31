package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Category {
	
	@Min(1)
	private long categoryId;
	
	@NotBlank(message = "Category Name Is Required")
	@Pattern(regexp = "[a-zA-Z]+",message = "Category Name Contains Should Be Only Alphabates")
	private String categoryName;
	
	private String discription;
	
	@Min(1)
	@Max(80)
	private int discount;
	
	@Min(1)
	@Max(18)
	private int gst;
	
	@Min(1)
	private float deliveryCharge;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(long categoryId, String categoryName, String discription, int discount, int gst,
			float deliveryCharge) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.discription = discription;
		this.discount = discount;
		this.gst = gst;
		this.deliveryCharge = deliveryCharge;
	}

	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getGst() {
		return gst;
	}


	public void setGst(int gst) {
		this.gst = gst;
	}


	public float getDeliveryCharge() {
		return deliveryCharge;
	}


	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	
	
}
