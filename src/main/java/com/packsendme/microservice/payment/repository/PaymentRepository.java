package com.packsendme.microservice.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaymentRepository extends MongoRepository<PaymentModel, String>   {
	

}