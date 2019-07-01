package com.packsendme.microservice.payment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface PaymentMethodRepository extends MongoRepository<PaymentMethodModel, String>   {
	
	@Query("{'status' :  {$eq: ?0}}")
	List<PaymentMethodModel> findAllPaymentMethodByActived(String status);


}