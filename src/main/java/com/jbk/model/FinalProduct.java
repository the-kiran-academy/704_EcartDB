package com.jbk.model;

public class FinalProduct extends Product{

	private Charges charges;
	private double discountAmount;
	private double finalProductPrice;
	
	public FinalProduct() {
		// TODO Auto-generated constructor stub
	}

	public FinalProduct(Charges charges, double discountAmount, double finalProductPrice) {
		super();
		this.charges = charges;
		this.discountAmount = discountAmount;
		this.finalProductPrice = finalProductPrice;
	}

	public Charges getCharges() {
		return charges;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalProductPrice() {
		return finalProductPrice;
	}

	public void setFinalProductPrice(double finalProductPrice) {
		this.finalProductPrice = finalProductPrice;
	}
	
	
	
}
