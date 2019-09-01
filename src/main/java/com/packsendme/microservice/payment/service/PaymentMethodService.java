package com.packsendme.microservice.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClientException;
import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.constants.MicroservicesConstants;
import com.packsendme.lib.common.response.Response;
import com.packsendme.microservice.payment.dao.PaymentMethodDAO;
import com.packsendme.microservice.payment.dto.CardPayDto;
import com.packsendme.microservice.payment.dto.PaymentListDto;
import com.packsendme.microservice.payment.dto.ResultValidationCardDto;
import com.packsendme.microservice.payment.repository.PaymentMethodModel;

@Service
@ComponentScan(basePackages = {"com.packsendme.microservice.payment.dao"})
public class PaymentMethodService {
	
	@Autowired
	private PaymentMethodDAO methodPaymentDAO;

 
	
	public ResponseEntity<?> savePaymentMethod(String namePayMethod) throws Exception {
		PaymentMethodModel entity = new PaymentMethodModel();
		Response<PaymentMethodModel> responseObj = new Response<PaymentMethodModel>(HttpExceptionPackSend.CREATE_PAYMENT.getAction(), null);
		try {
			entity.setNamePayMethod(namePayMethod);
			entity.setStatus(MicroservicesConstants.STATUS_ACTIVE);
			methodPaymentDAO.add(entity); 
			return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> updatePaymentMethod(String namePayMethodOld, String namePayMethod, String status) throws Exception {
		PaymentMethodModel entity = new PaymentMethodModel();
		Response<PaymentMethodModel> responseObj = new Response<PaymentMethodModel>(HttpExceptionPackSend.UPDATE_USERNAME.getAction(), entity);
		try {
			entity.setNamePayMethod(namePayMethodOld);
			PaymentMethodModel payMethod = methodPaymentDAO.find(entity);
			if(payMethod != null) {
				payMethod.setNamePayMethod(namePayMethod);
				payMethod.setStatus(status);
				entity = methodPaymentDAO.update(payMethod);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	public ResponseEntity<?> getPaymentMethodActived() throws Exception {
		List<PaymentMethodModel> payMethodL = new ArrayList<PaymentMethodModel>();
		PaymentListDto paymentDTO = new PaymentListDto();
		try {
			payMethodL = methodPaymentDAO.findAll();
			if(payMethodL.size() >= 1){
				paymentDTO.setPayment(payMethodL);
				Response<PaymentListDto> responseObj = new Response<PaymentListDto>(HttpExceptionPackSend.FOUND_PAYMENT.getAction(), paymentDTO);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else{
				Response<PaymentMethodModel> responseObj = new Response<PaymentMethodModel>(HttpExceptionPackSend.FOUND_PAYMENT.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			Response<PaymentMethodModel> responseErrorObj = new Response<PaymentMethodModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), null);
			return new ResponseEntity<>(responseErrorObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<?> deleteMethoPayment(String payName) throws Exception {
		PaymentMethodModel entity = new PaymentMethodModel();
		Response<PaymentMethodModel> responseObj = new Response<PaymentMethodModel>(HttpExceptionPackSend.ACCOUNT_DELETE.getAction(), entity);
		try {
			entity.setNamePayMethod(payName);
			entity = methodPaymentDAO.find(entity);

			if(entity != null) {
				methodPaymentDAO.remove(entity);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<?> getValidationCardEntity(String payCodenum,String payCountry,String payEntity,String payValue,String payExpiry) throws Exception {
		try {
			/// Call Entity Card Validation

			ResultValidationCardDto resultCardObj = new ResultValidationCardDto();
			PaymentValidationCardMock payMock = new PaymentValidationCardMock(); 
			
			if(payValue == "000"){
				resultCardObj = payMock.testValidation_Fail();
				Response<ResultValidationCardDto> responseObj = new Response<ResultValidationCardDto>(HttpExceptionPackSend.FOUND_PAYMENT.getAction(), resultCardObj);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else{
				resultCardObj = payMock.testValidation_OK();
				Response<ResultValidationCardDto> responseObj = new Response<ResultValidationCardDto>(HttpExceptionPackSend.FOUND_PAYMENT.getAction(), resultCardObj);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			Response<CardPayDto> responseErrorObj = new Response<CardPayDto>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), null);
			return new ResponseEntity<>(responseErrorObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	


	
}
