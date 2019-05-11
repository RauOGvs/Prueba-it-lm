package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account_type_details database table.
 * 
 */
@Entity
@Table(name="account_type_details")
@NamedQuery(name="AccountTypeDetail.findAll", query="SELECT a FROM AccountTypeDetail a")
public class AccountTypeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cc_account_type_detail_id")
	private Integer ccAccountTypeDetailId;

	@Column(name="cc_account_type_detail_name")
	private String ccAccountTypeDetailName;

	//bi-directional many-to-one association to TypeAccount
	@ManyToOne
	@JoinColumn(name="cc_account_type_detail_type_id")
	private TypeAccount typeAccount;

	//bi-directional many-to-one association to ClientAccount
	@OneToMany(mappedBy="accountTypeDetail")
	private List<ClientAccount> clientAccounts;

	public AccountTypeDetail() {
	}

	public Integer getCcAccountTypeDetailId() {
		return this.ccAccountTypeDetailId;
	}

	public void setCcAccountTypeDetailId(Integer ccAccountTypeDetailId) {
		this.ccAccountTypeDetailId = ccAccountTypeDetailId;
	}

	public String getCcAccountTypeDetailName() {
		return this.ccAccountTypeDetailName;
	}

	public void setCcAccountTypeDetailName(String ccAccountTypeDetailName) {
		this.ccAccountTypeDetailName = ccAccountTypeDetailName;
	}

	public TypeAccount getTypeAccount() {
		return this.typeAccount;
	}

	public void setTypeAccount(TypeAccount typeAccount) {
		this.typeAccount = typeAccount;
	}

	public List<ClientAccount> getClientAccounts() {
		return this.clientAccounts;
	}

	public void setClientAccounts(List<ClientAccount> clientAccounts) {
		this.clientAccounts = clientAccounts;
	}

	public ClientAccount addClientAccount(ClientAccount clientAccount) {
		getClientAccounts().add(clientAccount);
		clientAccount.setAccountTypeDetail(this);

		return clientAccount;
	}

	public ClientAccount removeClientAccount(ClientAccount clientAccount) {
		getClientAccounts().remove(clientAccount);
		clientAccount.setAccountTypeDetail(null);

		return clientAccount;
	}

}