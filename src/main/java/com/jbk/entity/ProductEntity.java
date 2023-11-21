package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@Column(name = "product_id",unique = true,nullable = false)
	private long productId;
	
	@Column(name = "product_name",unique = true,nullable = false)
	private String productName;
	
	@OneToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplier;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	@Column(name = "product_qty",nullable = false)
	private int productQty;
	
	@Column(name = "product_price",nullable = false)
	private double productPrice;
	
	
	public ProductEntity() {
		// TODO Auto-generated constructor stub
	}


	public ProductEntity(long productId, String productName, SupplierEntity supplier, CategoryEntity category, int productQty,
			double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public SupplierEntity getSupplier() {
		return supplier;
	}


	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}


	public CategoryEntity getCategory() {
		return category;
	}


	public void setCategory(CategoryEntity category) {
		this.category = category;
	}


	public int getProductQty() {
		return productQty;
	}


	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
