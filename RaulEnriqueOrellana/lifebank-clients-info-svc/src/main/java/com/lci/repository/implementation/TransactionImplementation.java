package com.lci.repository.implementation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lci.entity.AccountTrasaction;
import com.lci.repository.ITransactions;

@Component
public class TransactionImplementation implements ITransactions<String, Date, AccountTrasaction> {

	private Logger log;
	@PersistenceContext
	private EntityManager em;

	public TransactionImplementation() {
		this.log = LoggerFactory.getLogger(getClass());
	}

	@Override
	public List<AccountTrasaction> getTransactions(String accountId, Date initDate, Date finalDate) {

		try {
			String sql = "SELECT act FROM AccountTrasaction act "
					+ "where act.clientAccount.ccClientAccountId=:accountId "
					+ "and act.tTransactionDate between :initDate and :finalDate "
					+ "order by act.tTransactionDate DESC";
			Query q = em.createQuery(sql).setParameter("accountId", accountId).setParameter("initDate", initDate)
					.setParameter("finalDate", finalDate);
			List<AccountTrasaction> result = q.getResultList();
			return result;

		} catch (Exception e) {
			log.error("Microservicio lifebank-client-info-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}

		return null;
	}

}
