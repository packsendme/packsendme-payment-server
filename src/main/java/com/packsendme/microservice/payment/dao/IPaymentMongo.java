package com.packsendme.microservice.payment.dao;

import java.util.List;


public interface IPaymentMongo<T> {

	public T add(T entity);

	public T find(T account);
	
	public List<T> findAll();
	
	public void remove(T entity);
	
	public T update(T entity);
	
	
		

}
