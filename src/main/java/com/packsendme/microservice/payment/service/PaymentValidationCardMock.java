package com.packsendme.microservice.payment.service;

import org.springframework.stereotype.Component;

import com.packsendme.microservice.payment.dto.ResultValidationCardDto;

@Component
public class PaymentValidationCardMock {

	public ResultValidationCardDto testValidation_OK() {
		ResultValidationCardDto resultCardSuccess = new ResultValidationCardDto();
		resultCardSuccess.setCod("100");
		resultCardSuccess.setMsg("SUCCESS");
		return resultCardSuccess;
	}

	public ResultValidationCardDto testValidation_Fail() {
		ResultValidationCardDto resultCardError = new ResultValidationCardDto();
		resultCardError.setCod("-100");
		resultCardError.setMsg("ERROR");
		return resultCardError;
	}
}
