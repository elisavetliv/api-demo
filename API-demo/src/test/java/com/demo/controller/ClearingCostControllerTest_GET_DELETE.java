package com.demo.controller;

import org.springframework.http.HttpHeaders;

import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClearingCostControllerTest_GET_DELETE {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
	@Test
	public void C_testGetter() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth("api_tester", "test")
				.exchange(
				createURLWithPort("/clearing-cost/GR"),
				HttpMethod.GET, entity, String.class);

		
		String expected = "{\"country\":\"GR\",\"cost\":\"10\"}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void D_testDelete() {


		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth("api_tester", "test")
				.exchange(
				createURLWithPort("/clearing-cost/GR"),
				HttpMethod.DELETE, entity, String.class);
		
		HttpStatus statusCode = response.getStatusCode();
		
		assertTrue(statusCode==HttpStatus.OK);

	}
	




}

