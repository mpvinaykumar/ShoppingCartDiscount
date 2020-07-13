package com.example.ShoppingCart.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShoppingCartForm {

	@NotNull
	@Size(min = 2, max = 14, message = "The customerType '${validatedValue}' must be between {min} and {max} characters long")
	private String customerType;

	@NotNull
	@Min(0)
	private Double billAmount;

	
	private Double discount;

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	

}
