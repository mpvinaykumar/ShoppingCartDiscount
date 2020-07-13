package com.example.ShoppingCart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkCartInfoWhenBillAmountMissingThenFailure() throws Exception {
		MockHttpServletRequestBuilder createCart = post("/").param("customerType", "regular");

		mockMvc.perform(createCart).andExpect(model().hasErrors());
	}

	@Test
	public void checkCartInfoWhenCustomerTypeMissingThenFailure() throws Exception {
		MockHttpServletRequestBuilder createCart = post("/").param("billAmount", "5000");
		mockMvc.perform(createCart).andExpect(model().hasErrors());
	}

	@Test
	public void checkCartInfoForRegularThenSuccess() throws Exception {
		MockHttpServletRequestBuilder createCart = post("/").param("customerType", "Regular").param("billAmount",
				"5000");

		mockMvc.perform(createCart).andExpect(status().isOk());
	}

	@Test
	public void checkCartInfoForPremiumThenSuccess() throws Exception {
		MockHttpServletRequestBuilder createCart = post("/").param("customerType", "Premium").param("billAmount",
				"5000");

		mockMvc.perform(createCart).andExpect(status().isOk());
	}

}
