package com.example.ShoppingCart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.ShoppingCart.model.ShoppingCartForm;
import com.example.ShoppingCart.service.impl.CalculateDiscount;

@Controller
public class WebController implements WebMvcConfigurer {
	
	@Autowired
	CalculateDiscount calcDiscount;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(ShoppingCartForm shoppingCartForm) {
		return "form";
	}

	@PostMapping("/")
	public ModelAndView  checkPersonInfo(@Valid ShoppingCartForm cartForm, BindingResult bindingResult) {

		ModelAndView mavForm = new ModelAndView("form");
		
		if (bindingResult.hasErrors()) {
			return mavForm;
		}
		
		calcDiscount.discountDriver(cartForm);
		mavForm.addObject("shoppingCartForm", cartForm);

		return mavForm;
	}
}