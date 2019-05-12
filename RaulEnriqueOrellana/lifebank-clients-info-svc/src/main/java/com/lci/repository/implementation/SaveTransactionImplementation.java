package com.lci.repository.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lci.entity.AccountTrasaction;
import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.repository.ISaveTrasaction;

@Component
public class SaveTransactionImplementation {

	private ISaveTrasaction saveTransaction;
	private Logger log;

	public SaveTransactionImplementation(ISaveTrasaction saveTransaction) {
		this.log = LoggerFactory.getLogger(getClass());
		this.saveTransaction = saveTransaction;
	}

	/*
	 * 1 = salida (-)
	 * 2 = entrada
	 * */
	public String saveTransaction(String detail, double amount, String clientId, String accountId, int type) {
		try {
			AccountTrasaction act = new AccountTrasaction();
			Timestamp time = new Timestamp(new Date().getTime());

			Calendar cal = Calendar.getInstance();
			ClientAccount clientAccount = new ClientAccount();
			Client client = new Client();
			int anio = cal.get(Calendar.YEAR);
			StringBuilder sb = new StringBuilder();
			String[] timeS = time.toString().split(":");
			String separador = Pattern.quote(".");
			String[] firstText = timeS[0].split(" ");
			String[] timeMilis = timeS[2].toString().split(separador);
			sb.append("BT-").append(firstText[0]).append(firstText[1]).append(timeS[1]).append(timeMilis[0])
					.append(timeMilis[1]).append(anio);
			act.setTTrasactionId(sb.toString());
			act.setTTransactionDate(time);
			if (type == 1) {
				act.setTTransactionAmount(BigDecimal.valueOf(amount*-1));
			} else {
				act.setTTransactionAmount(BigDecimal.valueOf(amount));
			}
			act.setTTransactionDetail(detail);
			client.setCcClientId(clientId);
			clientAccount.setClient(client);
			clientAccount.setCcClientAccountId(accountId);
			act.setClientAccount(clientAccount);
			StringBuilder auth = new StringBuilder();
			auth.append(firstText[0]).append(firstText[1]).append(timeS[1]).append(timeMilis[0])
			.append(timeMilis[1]).append(anio);
			act.setTAutorization(auth.toString());
			if (saveTransaction.save(act) != null) {
				return auth.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return null;
		}

	}

}
