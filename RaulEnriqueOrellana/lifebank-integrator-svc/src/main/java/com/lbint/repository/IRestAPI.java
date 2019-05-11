package com.lbint.repository;

import com.lbint.service.pojo.response.AccountClientPojo;

public interface IRestAPI {
	
	
	public AccountClientPojo getAccountsClient(String clientId);

}
