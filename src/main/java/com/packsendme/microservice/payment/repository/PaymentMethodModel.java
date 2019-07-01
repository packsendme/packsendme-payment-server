package com.packsendme.microservice.payment.repository;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PaymentMethod")
public class PaymentMethodModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String namePayMethod;
	private String status;
	
	
	public String getNamePayMethod() {
		return namePayMethod;
	}
	public void setNamePayMethod(String namePayMethod) {
		this.namePayMethod = namePayMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
