package com.packsendme.microservice.payment.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
 

@Service
@ComponentScan(basePackages = {"com.packsendme.microservice.payment.dao"})
public class PaymentService {
	
	//@Autowired
	//private paymentDAO accountDAO;

 
	/*
	public ResponseEntity<?> registerAccount(AccountDto accountDto) throws Exception {
		AccountModel accountSave = null;
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.CREATED_ACCOUNT.getAction(), accountSave);
		AccountModel account = new AccountModel();
		account = convertToEntity(accountDto);
		Date dtCreation = convertObj.convertStringToDate(accountDto.getDateCreation());
		account.setDateCreation(dtCreation);
		try {
			accountSave = accountDAO.add(account); 
			if(accountSave != null) {
				// Call IAMService - To allows User Access 
				ResponseEntity<?> userAccessEnable = iamClient.createUser(account.getUsername(),
						account.getPassword(), accountDto.getDateCreation());
				if(userAccessEnable.getStatusCode() == HttpStatus.ACCEPTED) {
					return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
				}
				// Call IAMService - Error update that delete account
				else {
					accountDAO.remove(accountSave);
					return new ResponseEntity<>(responseObj, HttpStatus.BAD_GATEWAY);
				}
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			accountDAO.remove(accountSave);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> updateAccountByUsername(String username, String usernamenew, String dtAction) throws Exception {
		AccountModel entity = new AccountModel();
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.UPDATE_USERNAME.getAction(), entity);
		Date dtUpdate = convertObj.convertStringToDate(dtAction);
		try {
			entity.setUsername(username);
			AccountModel account = accountDAO.find(entity);
			if(account != null) {
				account.setDateUpdate(dtUpdate);
				account.setUsername(usernamenew);
				account = accountDAO.update(account);
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

	
	
	private AccountModel convertToEntity(AccountDto accountDto) {
		//AccountModel accountModel = modelMapper.map(accountDto, AccountDto.class);
		AccountModel accountModel = new AccountModel();
		BeanUtils.copyProperties(accountDto, accountModel);
		return accountModel;

	}
	
	public ResponseEntity<?> findPaymentUserByUsername(String username) throws Exception {
		AccountModel entity = new AccountModel();
		try {
			entity.setUsername(username);
			entity = accountDAO.find(entity);
			
			if(entity != null){
				PaymentAccountCollectionDto paymentAccountDto = paymentParser.parsePaymentAccountOperationFind(entity);
				Response<PaymentAccountCollectionDto> responseObj = new Response<PaymentAccountCollectionDto>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), paymentAccountDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), entity);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			Response<AccountModel> responseErrorObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), entity);
			return new ResponseEntity<>(responseErrorObj, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> updatePaymentAccountByUsername(PaymentAccountCRUDDto paymentDto) throws Exception {
		AccountModel entity = new AccountModel();
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.UPDATE_USERNAME.getAction(), entity);
		try {
			entity.setUsername(paymentDto.getUsername());
			entity = accountDAO.find(entity);

			if(entity != null) {
				AccountModel entityObj = paymentParser.parsePaymentAccountOperationEdit(entity,paymentDto);
				entity = accountDAO.update(entityObj);
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

	public ResponseEntity<?> deletePaymentAccountByUsername(PaymentAccountCRUDDto paymentDto) throws Exception {
		AccountModel entity = new AccountModel();
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.ACCOUNT_DELETE.getAction(), entity);
		try {
			entity.setUsername(paymentDto.getUsername());
			entity = accountDAO.find(entity);

			if(entity != null) {
				AccountModel entityObj = paymentParser.parsePaymentAccountOperationDelete(entity,paymentDto);
				entity = accountDAO.update(entityObj);
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
	
	public ResponseEntity<?> savePaymentAccountByUsername(PaymentAccountCRUDDto paymentDto) throws Exception {
		AccountModel entity = new AccountModel();
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.ACCOUNT_DELETE.getAction(), entity);
		try {
			entity.setUsername(paymentDto.getUsername());
			entity = accountDAO.find(entity);

			if(entity != null) {
				AccountModel entityObj = paymentParser.parsePaymentAccountOperationSave(entity,paymentDto);
				entity = accountDAO.update(entityObj);
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

	
	public ResponseEntity<?> findAccountToLoad(String username) {
		AccountModel entity = new AccountModel();
		try {
			entity.setUsername(username);
			entity = accountDAO.find(entity);
			if(entity != null){
				Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), entity);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), entity);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			Response<AccountModel> responseErrorObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_ACCOUNT.getAction(), entity);
			return new ResponseEntity<>(responseErrorObj, HttpStatus.NOT_FOUND);
		}
	}


	public ResponseEntity<?> updateAccountByAll(AccountDto accountDto) throws Exception {
		AccountModel entity = new AccountModel();
		entity = convertToEntity(accountDto);
		Date dtCreate = convertObj.convertStringToDate(accountDto.getDateCreation());
		Date dtUpdate = convertObj.convertStringToDate(accountDto.getDateUpdate());
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.UPDATE_ACCOUNT.getAction(), null);
		try {
			entity.setDateCreation(dtCreate);
			entity.setDateUpdate(dtUpdate);
			AccountModel accountFind = accountDAO.find(entity);
			if(accountFind != null) {
				entity.setId(accountFind.getId());
				entity = accountDAO.update(entity);
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
	
	public ResponseEntity<?> updateAddressAccountByUsername(AddressAccountDto addressAccount) throws Exception {


		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.UPDATE_ACCOUNT.getAction(), null);
		try {
			AccountModel accountObj = new AccountModel();
			accountObj.setUsername(addressAccount.getUsername());
			AccountModel accountFind = accountDAO.find(accountObj);
			
			// Parser Account Entity - Account Address
			AccountModel entity = accountParser.parseAddressDtoToAccountModel(accountFind, addressAccount);

			if(entity != null) {
				entity = accountDAO.update(entity);
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
	
	
	public ResponseEntity<?> findAccountByEmail(String email) {
		AccountModel accountEntity = new AccountModel();
		Response<AccountModel> responseObj = new Response<AccountModel>(HttpExceptionPackSend.FOUND_EMAIL.getAction(), null);
		try {
			accountEntity.setEmail(email);
			AccountModel entity = accountDAO.find(accountEntity);
			if(entity != null) {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (MongoClientException e ) {
			e.printStackTrace();
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
}
