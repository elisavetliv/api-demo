package com.demo.controller;


import org.springframework.web.bind.annotation.RestController;

import com.demo.model.data.ElasticsearchData;
import com.demo.model.request.ClearingCostMatrix;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("clearing-cost")
public class ClearingCostController{
	
	ElasticsearchData elasticsearch = new ElasticsearchData();
			
	
	@GetMapping(path="/{country}")
	public String getClearingCost(@PathVariable String country) {
		
		return elasticsearch.getData(country);
		
	}
	
	@PostMapping
	public String createClearingCost(@RequestBody ClearingCostMatrix country_cost) {
		String country=country_cost.getCountry();
		String cost=country_cost.getCost();
		elasticsearch.deleteData(country);
		elasticsearch.postData(country, cost);
		
		return "Clearing cost for country "+country+" has been created and set to "+cost+" dollars";
	}
	
	@PutMapping
	public String updateClearingCost(@RequestBody ClearingCostMatrix country_cost) {
		String country=country_cost.getCountry();
		String newCost=country_cost.getCost();
		elasticsearch.putData(country, newCost);
		return "Clearing cost of country "+country+" is updated to "+newCost+" dollars";
	}
	
	@DeleteMapping(path="{country}")
	public String deleteClearingCost(@PathVariable String country) {
		if (!(country.equals("GR") || country.equals("US"))) {
			country= "Others";
		} 
		elasticsearch.deleteData(country);
		return "Clearing cost of country "+country+" is deleted";
	}
	
	
}
