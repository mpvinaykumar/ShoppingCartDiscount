package com.example.ShoppingCart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.model.ShoppingCartForm;
import com.example.ShoppingCart.service.CustomerType;
import com.example.ShoppingCart.util.CustomerTypeFactory;
import com.example.ShoppingCart.util.CustomerTypes;

@Service
public class CalculateDiscount {

	@Autowired
	CustomerTypeFactory customerTypeFactory;
	
	private CustomerType customerType;
	
	public void discountDriver(ShoppingCartForm cartForm) {
		
		try {

			CustomerTypes custTypeEnum = CustomerTypes.valueOf(cartForm.getCustomerType().toUpperCase());
			customerType = customerTypeFactory.getCustomerType(custTypeEnum);
			if(customerType != null) {
				Double discount = customerType.discount(cartForm.getBillAmount());
				cartForm.setDiscount(discount);
			}else {
				cartForm.setDiscount(null);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}


	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

}
