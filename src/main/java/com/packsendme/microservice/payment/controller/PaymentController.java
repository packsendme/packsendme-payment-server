package com.packsendme.microservice.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.packsendme.microservice.payment.dto.CardPayDto;
import com.packsendme.microservice.payment.dto.PaymentDto;
import com.packsendme.microservice.payment.service.PaymentMethodService;

@RestController
public class PaymentController {

	
	@Autowired
	private PaymentMethodService methodPayService; 
	
	
	//** BEGIN OPERATION: PAYMENT METHOD *************************************************//

	@PostMapping("/payment/method/{namePayMethod}}")
	public ResponseEntity<?> createPaymentMethod(@Validated @PathVariable ("namePayMethod") String namePayMethod) throws Exception {
		return methodPayService.savePaymentMethod(namePayMethod);
	}

	@GetMapping("/payment/method/")
	public ResponseEntity<?> loadAccount() throws Exception {
		return methodPayService.getPaymentMethodActived();
	}

	@PutMapping("/payment/method/{namePayMethodOld}/{namePayMethod}/{status}}")
	public ResponseEntity<?> changePaymentMethod(@Validated @PathVariable ("namePayMethodOld") String namePayMethodOld,
			@Validated @PathVariable ("namePayMethod") String namePayMethod,
			@Validated @PathVariable ("status") String status) throws Exception {
		return methodPayService.updatePaymentMethod(namePayMethodOld, namePayMethod, status);
	}
	
	
	//** BEGIN OPERATION: CARD VALIDATE  *************************************************//

	@GetMapping("/payment/card/validate/")
	public ResponseEntity<?> validateCreditCard(@Validated @RequestBody PaymentDto cardpayDto) throws Exception {
		return methodPayService.getValidationCardEntity(cardpayDto);
	}


}
