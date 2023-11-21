package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {
	
	@Id
	@Column(name = "category_id",unique = true,nullable = false)
	private long categoryId; 
	
	@Column(name = "category_name",unique = true,nullable = false)
	private String categoryName;
	
	@Column(name = "category_discription",unique = true)
	private String discription;
	
	@Column(name = "discount",nullable = false,columnDefinition = "int default 5")
	private int discount;
	
	@Column(name = "gst",nullable = false)
	private int gst;
	
	@Column(name = "delivery_charge",nullable = false)
	private float deliveryCharge;
	
	
	public CategoryEntity() {
		// TODO Auto-generated constructor stub
	}


	public CategoryEntity(long categoryId, String categoryName, String discription, int discount, int gst,
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


	@Override
	public String toString() {
		return "CategoryEntity [categoryId=" + categoryId + ", categoryName=" + categoryName + ", discription="
				+ discription + ", discount=" + discount + ", gst=" + gst + ", deliveryCharge=" + deliveryCharge + "]";
	}
	
	
}
