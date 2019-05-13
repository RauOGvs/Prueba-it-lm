package com.lci.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lci.entity.ClientAccount;
import com.lci.repository.implementation.ValidateAccountImplementation;
import com.lci.response.ValueResponse;

@Service
public class ValidateAccountTypeProcess {

	private Logger log;
	private ValidateAccountImplementation validateAccount;

	public ValidateAccountTypeProcess(ValidateAccountImplementation validateAccount) {
		this.log = LoggerFactory.getLogger(getClass());
		this.validateAccount = validateAccount;
	}

	public ValueResponse<String> validateAccountProcess(String accountID) {

		try {
			log.info("Entrada: {}", accountID);
			ClientAccount account = validateAccount.getAccount(accountID);

			if (account != null) {
				ValueResponse<String> response = new ValueResponse<>("200", account.getClient().getCcClientId(), "OK");
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return response;
			} else {
				ValueResponse<String> response = new ValueResponse<>("201", null, "NO DATA");
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
