package com.demo.model.request;

public class ClearingCostMatrix {
	private String country,cost;

	public ClearingCostMatrix(String country, String cost) {
		this.country = country;
		this.cost = cost;
	}

	public String getCountry() {
		return country;
	}

	public String getCost() {
		return cost;
	}
	
	

}
