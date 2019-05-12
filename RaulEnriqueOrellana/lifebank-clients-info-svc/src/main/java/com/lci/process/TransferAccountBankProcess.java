package com.lci.process;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lci.entity.AccountTypeDetail;
import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.repository.IValidateAccount;
import com.lci.repository.implementation.SaveTransactionImplementation;
import com.lci.service.pojo.request.TransferAccountRequest;

@Service
public class TransferAccountBankProcess {

	private Logger log;
	private IValidateAccount doTranser;
	private SaveTransactionImplementation  saveT;
	public TransferAccountBankProcess(IValidateAccount doTranser,SaveTransactionImplementation  saveT) {
		this.log = LoggerFactory.getLogger(getClass());
		this.doTranser = doTranser;
		this.saveT = saveT;
	}

	@Transactional
	public String transferAccountProcess(TransferAccountRequest request)
			throws RuntimeException {

		
		ClientAccount accountOrigin = new ClientAccount();
		ClientAccount accountDestination = new ClientAccount();
		Client client = new Client();
		AccountTypeDetail atd = new AccountTypeDetail();
		String accountIdOrigin =request.getAccountIdOrigin();
		String accountIdDestination = request.getAccountIdDestination();
		double amount = request.getAmount();
		String clientId = request.getClientId();
		int typeAccount = request.getTypeAccount();
		double amountOrigin = request.getAmountOrigin();
		double amountDestination = request.getAmountDestination();
		StringBuilder  sb = new StringBuilder();
		try {
			log.info("Request: {}", new ObjectMapper().writeValueAsString(request));
			client.setCcClientId(clientId);

			atd.setCcAccountTypeDetailId(typeAccount);
			accountOrigin.setAccountTypeDetail(atd);
			accountOrigin.setClient(client);
			accountOrigin.setCcClientAccountId(accountIdOrigin);
			accountOrigin.setCcAmount(BigDecimal.valueOf(amountOrigin - amount));

			accountDestination.setAccountTypeDetail(atd);
			accountDestination.setClient(client);
			accountDestination.setCcClientAccountId(accountIdDestination);
			accountDestination.setCcAmount(BigDecimal.valueOf(amountDestination + amount));
			ClientAccount result1 = doTranser.save(accountOrigin);
			ClientAccount result2 = doTranser.save(accountDestination);
			if ( result1 != null &&  result2 != null) {
				log.info("SUCCESS");
				sb.append("Transferencia a ").append(accountIdDestination);
				String auth =saveT.saveTransaction(sb.toString(), amount, clientId, accountIdOrigin, 1);
				if(auth!=null) {
					return auth;
				}
				
			} else {
				throw new RuntimeException("hubo un error al hacer el movimiento");
			}

		} catch (Exception e) {
			log.error("Microservicio lifebank-client-info-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			throw new RuntimeException("hubo un error al hacer el movimiento");
		}
		return null;
	}
}
