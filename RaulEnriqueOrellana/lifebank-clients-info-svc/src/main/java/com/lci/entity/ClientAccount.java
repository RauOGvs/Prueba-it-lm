package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the client_accounts database table.
 * 
 */
@Entity
@Table(name="client_accounts")
@NamedQuery(name="ClientAccount.findAll", query="SELECT c FROM ClientAccount c")
public class ClientAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cc_client_account_id")
	private String ccClientAccountId;

	@Column(name="cc_amount")
	private BigDecimal ccAmount;

	//bi-directional many-to-one association to AccountTrasaction
	@OneToMany(mappedBy="clientAccount")
	private List<AccountTrasaction> accountTrasactions;

	//bi-directional many-to-one association to AccountTypeDetail
	@ManyToOne
	@JoinColumn(name="cc_client_type_account_id")
	private AccountTypeDetail accountTypeDetail;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="cc_client_account_client_id")
	private Client client;

	public ClientAccount() {
	}

	public String getCcClientAccountId() {
		return this.ccClientAccountId;
	}

	public void setCcClientAccountId(String ccClientAccountId) {
		this.ccClientAccountId = ccClientAccountId;
	}

	public BigDecimal getCcAmount() {
		return this.ccAmount;
	}

	public void setCcAmount(BigDecimal ccAmount) {
		this.ccAmount = ccAmount;
	}

	public List<AccountTrasaction> getAccountTrasactions() {
		return this.accountTrasactions;
	}

	public void setAccountTrasactions(List<AccountTrasaction> accountTrasactions) {
		this.accountTrasactions = accountTrasactions;
	}

	public AccountTrasaction addAccountTrasaction(AccountTrasaction accountTrasaction) {
		getAccountTrasactions().add(accountTrasaction);
		accountTrasaction.setClientAccount(this);

		return accountTrasaction;
	}

	public AccountTrasaction removeAccountTrasaction(AccountTrasaction accountTrasaction) {
		getAccountTrasactions().remove(accountTrasaction);
		accountTrasaction.setClientAccount(null);

		return accountTrasaction;
	}

	public AccountTypeDetail getAccountTypeDetail() {
		return this.accountTypeDetail;
	}

	public void setAccountTypeDetail(AccountTypeDetail accountTypeDetail) {
		this.accountTypeDetail = accountTypeDetail;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}