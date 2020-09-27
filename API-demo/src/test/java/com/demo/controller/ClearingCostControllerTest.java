package com.demo.controller;

import org.springframework.http.HttpHeaders;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.model.request.ClearingCostMatrix;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClearingCostControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void A_testPost() {

		ClearingCostMatrix cost1 = new ClearingCostMatrix("GR","109");
		ClearingCostMatrix cost2 = new ClearingCostMatrix("Others","15");
		ClearingCostMatrix[] costList = new ClearingCostMatrix[]{cost1,cost2};
		
		for (ClearingCostMatrix c: costList) {
			HttpEntity<ClearingCostMatrix> entity = new HttpEntity<ClearingCostMatrix>(c, headers);

			ResponseEntity<String> response = restTemplate.withBasicAuth("api_tester", "test")
					.exchange(
					createURLWithPort("/clearing-cost"),
					HttpMethod.POST, entity, String.class);
			
			HttpStatus statusCode = response.getStatusCode();
			
			assertTrue(statusCode==HttpStatus.OK);
		}
		

	}
	

	@Test
	public void B_testPut() {

		ClearingCostMatrix costMatrix = new ClearingCostMatrix("GR","10");

		HttpEntity<ClearingCostMatrix> entity = new HttpEntity<ClearingCostMatrix>(costMatrix, headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth("api_tester", "test")
				.exchange(
				createURLWithPort("/clearing-cost"),
				HttpMethod.POST, entity, String.class);
		
		HttpStatus statusCode = response.getStatusCode();
		
		assertTrue(statusCode==HttpStatus.OK);

	}
	




}

