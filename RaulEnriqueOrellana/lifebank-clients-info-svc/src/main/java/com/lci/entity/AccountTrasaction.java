package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the account_trasactions database table.
 * 
 */
@Entity
@Table(name="account_trasactions")
@NamedQuery(name="AccountTrasaction.findAll", query="SELECT a FROM AccountTrasaction a")
public class AccountTrasaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="t_trasaction_id")
	private String tTrasactionId;

	@Column(name="t_autorization")
	private String tAutorization;

	@Column(name="t_transaction_amount")
	private BigDecimal tTransactionAmount;

	@Column(name="t_transaction_date")
	private Timestamp tTransactionDate;

	@Column(name="t_transaction_detail")
	private String tTransactionDetail;

	//bi-directional many-to-one association to ClientAccount
	@ManyToOne
	@JoinColumn(name="t_trasaction_account_id")
	private ClientAccount clientAccount;

	public AccountTrasaction() {
	}

	public String getTTrasactionId() {
		return this.tTrasactionId;
	}

	public void setTTrasactionId(String tTrasactionId) {
		this.tTrasactionId = tTrasactionId;
	}

	public String getTAutorization() {
		return this.tAutorization;
	}

	public void setTAutorization(String tAutorization) {
		this.tAutorization = tAutorization;
	}

	public BigDecimal getTTransactionAmount() {
		return this.tTransactionAmount;
	}

	public void setTTransactionAmount(BigDecimal tTransactionAmount) {
		this.tTransactionAmount = tTransactionAmount;
	}

	public Timestamp getTTransactionDate() {
		return this.tTransactionDate;
	}

	public void setTTransactionDate(Timestamp tTransactionDate) {
		this.tTransactionDate = tTransactionDate;
	}

	public String getTTransactionDetail() {
		return this.tTransactionDetail;
	}

	public void setTTransactionDetail(String tTransactionDetail) {
		this.tTransactionDetail = tTransactionDetail;
	}

	public ClientAccount getClientAccount() {
		return this.clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}

}