package com.lbint.repository;

import com.lbint.service.pojo.request.TransferAccountPojo;
import com.lbint.service.pojo.response.AccountClientPojo;
import com.lbint.service.pojo.response.AccountTransactionPojo;
import com.lbint.service.pojo.response.ClientAccountResponse;

public interface IRestAPI {
	
	public AccountClientPojo getAccountsClient(String clientId);
	
	public AccountTransactionPojo getTrasactions(String... params);
	
	public String validateAccount(String... params);
	
	public ClientAccountResponse validateAccountBank(String... params);
	
	public String doTransfer(TransferAccountPojo request);

}
