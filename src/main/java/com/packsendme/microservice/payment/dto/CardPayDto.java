 package com.packsendme.microservice.payment.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CardPayDto")
public class CardPayDto{ 


    private String payEntity;
	private String payCodenum;
	private String payExpiry;
	private String payValue;
	private String payCountry;
	
	public String getPayEntity() {
		return payEntity;
	}
	public void setPayEntity(String payEntity) {
		this.payEntity = payEntity;
	}
	public String getPayCodenum() {
		return payCodenum;
	}
	public void setPayCodenum(String payCodenum) {
		this.payCodenum = payCodenum;
	}
	public String getPayExpiry() {
		return payExpiry;
	}
	public void setPayExpiry(String payExpiry) {
		this.payExpiry = payExpiry;
	}
	public String getPayValue() {
		return payValue;
	}
	public void setPayValue(String payValue) {
		this.payValue = payValue;
	}
	public String getPayCountry() {
		return payCountry;
	}
	public void setPayCountry(String payCountry) {
		this.payCountry = payCountry;
	}
 

	
}
