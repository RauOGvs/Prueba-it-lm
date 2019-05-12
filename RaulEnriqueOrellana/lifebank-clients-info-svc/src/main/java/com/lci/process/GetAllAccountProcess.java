package com.lci.process;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.repository.implementation.GetAccountsImplementation;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.AccountClientPojo;
import com.lci.service.pojo.response.Accounts;
import com.lci.service.pojo.response.CreditCard;
import com.lci.service.pojo.response.Loan;
import com.lci.service.pojo.response.Personal;

@Service
public class GetAllAccountProcess {

	private Logger log;
	private GetAccountsImplementation getAccounts;

	public GetAllAccountProcess(GetAccountsImplementation getAccounts) {
		this.log = LoggerFactory.getLogger(getClass());
		this.getAccounts = getAccounts;

	}

	public ValueResponse<AccountClientPojo> getAccount(String clientId) {
		Client client = new Client();
		client.setCcClientId(clientId);
		List<Loan> listLoan = new ArrayList<>();
		Loan loan = new Loan();
		List<CreditCard> listCredit = new ArrayList<>();
		CreditCard cCard = new CreditCard();
		List<Personal> listPersonals = new ArrayList<>();
		Personal personal = new Personal();
		AccountClientPojo response = new AccountClientPojo();
		Accounts accounts = new Accounts();
		try {
			List<ClientAccount> clientAccount = getAccounts.getAllAccounts(client);
			if (clientAccount != null) {
				for (ClientAccount c : clientAccount) {

					switch (c.getAccountTypeDetail().getTypeAccount().getCcTypeAccountName()) {

					case "loan": {
						loan.setId(String.valueOf(c.getAccountTypeDetail().getClientAccounts().get(0).getCcClientAccountId()));
						loan.setName(c.getAccountTypeDetail().getCcAccountTypeDetailName());
						listLoan.add(loan);
						loan = new Loan();
						break;
					}
					case "creditCards": {
						cCard.setId(String.valueOf(c.getAccountTypeDetail().getClientAccounts().get(0).getCcClientAccountId()));
						cCard.setName(c.getAccountTypeDetail().getCcAccountTypeDetailName());
						listCredit.add(cCard);
						cCard = new CreditCard();
						break;
					}
					case "personal": {
						personal.setId(String.valueOf(c.getAccountTypeDetail().getClientAccounts().get(0).getCcClientAccountId()));
						personal.setName(c.getAccountTypeDetail().getCcAccountTypeDetailName());
						listPersonals.add(personal);
						personal = new Personal();
						break;
					}
					default:
						log.error("No se enconraron productos");
					}
				}
				accounts.setLoan(listLoan);
				accounts.setCreditCard(listCredit);
				accounts.setPersonal(listPersonals);
				response.setAccounts(accounts);
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return new ValueResponse<AccountClientPojo>("200", response, "SUCCESS");
			}else
				return new ValueResponse<AccountClientPojo>("201", null, "NO DATA");
				
		} catch (Exception e) {
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return new ValueResponse<AccountClientPojo>("201", null, "NO DATA");
		}

	}

}
