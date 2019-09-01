package com.packsendme.microservice.payment.service;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import com.packsendme.microservice.payment.dto.ResultValidationCardDto;

@Component
public class PaymentValidationCardMock {

	@Test
	public ResultValidationCardDto testValidation_OK() {
		ResultValidationCardDto resultCardSuccess = new ResultValidationCardDto();
		resultCardSuccess.setCod("100");
		resultCardSuccess.setMsg("SUCCESS");
		return resultCardSuccess;
	}

	@Test
	public ResultValidationCardDto testValidation_Fail() {
		ResultValidationCardDto resultCardError = new ResultValidationCardDto();
		resultCardError.setCod("-100");
		resultCardError.setMsg("ERROR");
		return resultCardError;
	}
}
