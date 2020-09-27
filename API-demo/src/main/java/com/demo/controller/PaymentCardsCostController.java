package com.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.data.BinlistData;
import com.demo.model.data.ElasticsearchData;
import com.demo.model.request.PaymentCardDetails;

@RestController
@RequestMapping("/payment-cards-cost")
public class PaymentCardsCostController {
	
	@PostMapping
	public String postCardCost(@RequestBody PaymentCardDetails panNumber) {
		String BIN = panNumber.getBIN();
		BinlistData binList = new BinlistData();
		String country = binList.getCountryFromBinlist(BIN);
		
		ElasticsearchData elasticsearch = new ElasticsearchData();
		
		return elasticsearch.getData(country);
	}
	
	

}
