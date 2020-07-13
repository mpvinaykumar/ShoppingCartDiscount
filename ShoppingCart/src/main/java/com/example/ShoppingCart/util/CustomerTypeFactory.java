package com.example.ShoppingCart.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ShoppingCart.service.CustomerType;
import com.example.ShoppingCart.service.impl.PremiumCustomer;
import com.example.ShoppingCart.service.impl.RegularCustomer;

@Component
public class CustomerTypeFactory {

	@Autowired
	private RegularCustomer regularCustomer;

	@Autowired
	private PremiumCustomer premiumCustomer;

	public CustomerType getCustomerType(CustomerTypes customerType) {

		switch (customerType) {
		case REGULAR:
			return regularCustomer;

		case PREMIUM:
			return premiumCustomer;

		default:
			return null;
		}

	}

}
