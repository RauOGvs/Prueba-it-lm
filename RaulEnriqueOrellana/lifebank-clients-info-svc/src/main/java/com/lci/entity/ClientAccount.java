package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;


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