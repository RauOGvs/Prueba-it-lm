package com.lci.repository.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.repository.IGetAccounts;

@Component
public class GetAccountsImplementation {

	private IGetAccounts igetAccounts;
	private Logger log;

	public GetAccountsImplementation(IGetAccounts igetAccounts) {
		this.log = LoggerFactory.getLogger(getClass());
		this.igetAccounts = igetAccounts;
	}

	public List<ClientAccount> getAllAccounts(Client client) {
		try {
			return igetAccounts.findByClient(client);
		} catch (Exception e) {
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return null;
		}

	}

}
