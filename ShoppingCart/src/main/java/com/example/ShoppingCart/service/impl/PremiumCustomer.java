package com.example.ShoppingCart.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.ShoppingCart.service.CustomerType;

@Component
public class PremiumCustomer implements CustomerType{

	@Value("#{${purchase.premium.map}}")
	Map<Float, String> slabMap;

	Double totalDiscount;

	@Override
	public Double discount(Double purchaseAmount) {
		// slabMap.entrySet().forEach(val -> System.out.println(val.getKey().toString()
		// + ':' + val.getValue()));

		totalDiscount = 0.0;

		for (Entry<Float, String> entry : slabMap.entrySet()) {
			Float discountPercent = entry.getKey();
			String[] discountRange = entry.getValue().split("-");

			try {
				Double minRange = Double.valueOf(discountRange[0]);
				Double maxRange = discountRange.length == 2 ? Double.valueOf(discountRange[1]) : Double.MAX_VALUE;

				if (maxRange <= purchaseAmount) {
					totalDiscount += ((maxRange - minRange) * discountPercent) / 100;
				} else if (purchaseAmount > minRange) {
					totalDiscount += ((purchaseAmount - minRange) * discountPercent) / 100;
				}

			} catch (NumberFormatException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}

		}

		return totalDiscount;
	}

}
