package com.packsendme.microservice.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.packsendme.microservice.payment.service.PaymentMethodService;

@RestController
public class PaymentController {

	
	@Autowired
	private PaymentMethodService methodPayService; 
	
	
	
	//** BEGIN OPERATION: CARD VALIDATE  *************************************************//
	
	@GetMapping("/payment/card/validate/{payCodenum}/{payCountry}/{payEntity}/{payValue}/{payExpiry}")
	public ResponseEntity<?> validateCreditCard(
			@Validated @PathVariable ("payCodenum") String payCodenum,
			@Validated @PathVariable ("payCountry") String payCountry,
			@Validated @PathVariable ("payEntity") String payEntity,
			@Validated @PathVariable ("payValue") String payValue,
			@Validated @PathVariable ("payExpiry") String payExpiry) throws Exception {
		return methodPayService.getValidationCardEntity(payCodenum,payCountry,payEntity,payValue,payExpiry);
	}


}
