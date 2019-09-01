package com.packsendme.microservice.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClientException;
import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.microservice.payment.dao.PaymentMethodDAO;
import com.packsendme.microservice.payment.dto.CardPayDto;
import com.packsendme.microservice.payment.dto.ResultValidationCardDto;

@Service
@ComponentScan(basePackages = {"com.packsendme.microservice.payment.dao"})
public class PaymentMethodService {
	
	@Autowired
	private PaymentMethodDAO methodPaymentDAO;

 
	public ResponseEntity<?> getValidationCardEntity(String payCodenum,String payCountry,String payEntity,String payValue,String payExpiry) throws Exception {
		try {
			/// Call Entity Card Validation
			ResultValidationCardDto resultCardObj = null;
			PaymentValidationCardMock payMock = new PaymentValidationCardMock(); 
			
			if(payValue == "000"){
				resultCardObj = payMock.testValidation_Fail();
				Response<ResultValidationCardDto> responseObj = new Response<ResultValidationCardDto>(HttpExceptionPackSend.NOT_VALIDATE_CARD.value(),
						HttpExceptionPackSend.NOT_VALIDATE_CARD.getAction(), resultCardObj);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else{
				resultCardObj = payMock.testValidation_OK();
				Response<ResultValidationCardDto> responseObj = new Response<ResultValidationCardDto>(HttpExceptionPackSend.VALIDATE_CARD.value(),
						HttpExceptionPackSend.VALIDATE_CARD.getAction(), resultCardObj);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			Response<CardPayDto> responseErrorObj = new Response<CardPayDto>(0,HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), null);
			return new ResponseEntity<>(responseErrorObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	


	
}
