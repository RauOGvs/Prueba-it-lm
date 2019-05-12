package com.lbint.repository;

import com.lbint.service.pojo.response.AccountClientPojo;
import com.lbint.service.pojo.response.AccountTransactionPojo;

public interface IRestAPI {
	
	public AccountClientPojo getAccountsClient(String clientId);
	
	public AccountTransactionPojo getTrasactions(String... params);
	
	public String validateAccount(String... params);

}
