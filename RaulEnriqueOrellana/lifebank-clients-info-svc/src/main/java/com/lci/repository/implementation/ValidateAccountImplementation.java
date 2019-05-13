package com.lci.repository.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lci.entity.ClientAccount;
import com.lci.repository.IValidateAccount;

@Component
public class ValidateAccountImplementation {

	private IValidateAccount ivalidateAccount;
	private Logger log;

	public ValidateAccountImplementation(IValidateAccount ivalidateAccount) {
		this.log = LoggerFactory.getLogger(getClass());
		this.ivalidateAccount = ivalidateAccount;
	}

	public ClientAccount getAccount(String accountID) {
		try {
			return ivalidateAccount.findById(accountID).get();
		} catch (Exception e) {
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return null;
		}

	}
}
