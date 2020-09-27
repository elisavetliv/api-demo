package com.demo.model.request;

public class PaymentCardDetails {
	
	private String card_number;
	

	public PaymentCardDetails() {
	}
	
	public PaymentCardDetails(String card_number) {
		this.card_number = card_number;
	}
	

	public String getCard_number() {
		return card_number;
	}

	public String getBIN() {
		if (card_number!=null) {
			return card_number.replace(" ","").substring(0,8);
		} else {
			return "PAN number has to be defined";
		}
		
		
	}

}
