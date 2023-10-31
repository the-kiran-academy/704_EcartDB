package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
	
	@Id
	@Column(name = "supplier_id",unique = true,nullable = false)
	private long supplierId;
	
	@Column(name = "supplier_name",unique = true,nullable = false)
	private String supplierName;
	
	@Column(name = "city",nullable = false)
	private String city;
	
	@Column(name = "postal_code",nullable = false)
	private int postalCode;
	
	@Column(name = "country_name",nullable = false)
	private String countryName;
	
	@Column(name = "mobile_number",unique = true,nullable = false)
	private String mobileNo;
	
	
	public SupplierEntity() {
		// TODO Auto-generated constructor stub
	}


	public SupplierEntity(long supplierId, String supplierName, String city, int postalCode, String countryName,
			String mobileNo) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.countryName = countryName;
		this.mobileNo = mobileNo;
	}


	public long getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}