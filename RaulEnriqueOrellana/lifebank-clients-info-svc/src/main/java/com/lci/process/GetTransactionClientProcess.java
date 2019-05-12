package com.lci.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lci.entity.AccountTrasaction;
import com.lci.repository.ITransactions;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.AccountTransactionPojo;
import com.lci.service.pojo.response.Transaction;

@Service
public class GetTransactionClientProcess {

	private Logger log;
	private ITransactions<String, Date, AccountTrasaction> itransaction;

	public GetTransactionClientProcess(ITransactions<String, Date, AccountTrasaction> itransaction) {
		this.log = LoggerFactory.getLogger(getClass());
		this.itransaction = itransaction;
	}

	public ValueResponse<AccountTransactionPojo> process(String accountID, String initDate, String endDate) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(initDate);
			Date finalDate = format.parse(endDate);
			AccountTransactionPojo accountPojo = new AccountTransactionPojo();
			List<Transaction> listT = new ArrayList<>();
			Transaction transaction = new Transaction();
			List<AccountTrasaction> listTransaction = itransaction.getTransactions(accountID, date, finalDate);
			if (listTransaction != null && !listTransaction.isEmpty()) {
				for (AccountTrasaction account : listTransaction) {
					transaction.setId(account.getTTrasactionId());
					transaction.setDescription(account.getTTransactionDetail());
					transaction.setDate(account.getTTransactionDate().toString());
					transaction.setAmount(account.getTTransactionAmount().toString());
					listT.add(transaction);
					transaction = new Transaction();
				}
				accountPojo.setEndDate(endDate);
				accountPojo.setStartDate(initDate);
				accountPojo.setId(accountID);
				accountPojo.setTransactions(listT);
				return new ValueResponse<AccountTransactionPojo>("200", accountPojo, "OK");
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-client-info-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return new ValueResponse<AccountTransactionPojo>("201", null, "NO DATA");
	}

}
