package com.packsendme.microservice.payment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.lib.common.constants.MicroservicesConstants;
import com.packsendme.microservice.payment.repository.PaymentMethodModel;
import com.packsendme.microservice.payment.repository.PaymentMethodRepository;

@Component("PaymentMethodDAO")
@ComponentScan(basePackages = {"com.packsendme.microservice.payment.dao"})
public class PaymentMethodDAO implements IPaymentMongo<PaymentMethodModel> {

	@Autowired
	PaymentMethodRepository paymentRepository;
	
	@Override
	public PaymentMethodModel add(PaymentMethodModel entity) {
		try {
			return  entity = paymentRepository.insert(entity);
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PaymentMethodModel find(PaymentMethodModel entity) {
		PaymentMethodModel payMethodObj = null;
		try {
			return payMethodObj;
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PaymentMethodModel> findAll() {
		List<PaymentMethodModel> payMethodObj = new ArrayList<PaymentMethodModel>();
		try {
			payMethodObj = paymentRepository.findAllPaymentMethodByActived(MicroservicesConstants.STATUS_ACTIVE);
			System.out.println("TAMANHO PAY LIST "+ payMethodObj.size());
			return payMethodObj;
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void remove(PaymentMethodModel entity) {
		try {
			paymentRepository.delete(entity);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PaymentMethodModel update(PaymentMethodModel entity) {
		try {
			return  entity = paymentRepository.save(entity);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
