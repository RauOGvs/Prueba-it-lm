package com.lci.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lci.entity.ClientAccount;
import com.lci.repository.implementation.ValidateAccountImplementation;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.ClientAccountResponse;

@Service
public class ValidateAccountProcess {

	private Logger log;
	private ValidateAccountImplementation validateAccount;

	public ValidateAccountProcess(ValidateAccountImplementation validateAccount) {
		this.log = LoggerFactory.getLogger(getClass());
		this.validateAccount = validateAccount;
	}

	public ValueResponse<ClientAccountResponse> validateAccountProcess(String accountID) {
		
			ClientAccountResponse clientAccount = new ClientAccountResponse();
		
		try {
			log.info("Entrada: {}", accountID);
			ClientAccount account = validateAccount.getAccount(accountID);
			
			if (account != null) {
				clientAccount.setAccountID(account.getCcClientAccountId());
				clientAccount.setClientID(account.getClient().getCcClientId());
				clientAccount.setTypeAccount(account.getAccountTypeDetail().getTypeAccount().getCcTypeAccountName());
				clientAccount.setAmount(Double.valueOf(account.getCcAmount().toString()));
				clientAccount.setTypeAccountInt(account.getAccountTypeDetail().getCcAccountTypeDetailId());
				ValueResponse<ClientAccountResponse> response = new ValueResponse<>("200", clientAccount, "OK");
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return response;
			} else {
				ValueResponse<ClientAccountResponse> response = new ValueResponse<>("201", null, "NO DATA");
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return response;
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-client-info-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return new ValueResponse<>("201", null, "NO DATA");
		}

	}

}
