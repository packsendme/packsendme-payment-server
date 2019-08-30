package com.packsendme.microservice.payment.dto;

import java.util.List;

public class PaymentListDto{

	public List<?> payment;
	
	public List<?> getPayment() {
		return payment;
	}
	public void setPayment(List<?> payment) {
		this.payment = payment;
	}

}
