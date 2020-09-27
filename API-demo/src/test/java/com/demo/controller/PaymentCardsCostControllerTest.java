package com.demo.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.request.PaymentCardDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentCardsCostControllerTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void testPost() {

		PaymentCardDetails pan = new PaymentCardDetails("4571 7360 1234 5678");

		HttpEntity<PaymentCardDetails> entity = new HttpEntity<PaymentCardDetails>(pan, headers);

		ResponseEntity<String> response = restTemplate .withBasicAuth("api_tester", "test")
				.exchange(
				createURLWithPort("/payment-cards-cost"),
				HttpMethod.POST, entity, String.class);
		
		String expected = "{\"country\":\"Others\",\"cost\":\"15\"}";
		
		try {
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	

}
