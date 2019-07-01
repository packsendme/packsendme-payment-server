package com.packsendme.microservice.payment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packsendme.microservice.payment.repository.PaymentModel;
import com.packsendme.microservice.payment.repository.PaymentRepository;

@Component("paymentDAO")
public class PaymentDAO implements IPaymentMongo<PaymentModel> {
	
	@Autowired
	PaymentRepository accountRepository;

	@Override
	public PaymentModel add(PaymentModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentModel find(PaymentModel account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(PaymentModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaymentModel update(PaymentModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
