package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type_accounts database table.
 * 
 */
@Entity
@Table(name="type_accounts")
@NamedQuery(name="TypeAccount.findAll", query="SELECT t FROM TypeAccount t")
public class TypeAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cc_type_account_id")
	private Integer ccTypeAccountId;

	@Column(name="cc_type_account_name")
	private String ccTypeAccountName;

	//bi-directional many-to-one association to AccountTypeDetail
	@OneToMany(mappedBy="typeAccount")
	private List<AccountTypeDetail> accountTypeDetails;

	public TypeAccount() {
	}

	public Integer getCcTypeAccountId() {
		return this.ccTypeAccountId;
	}

	public void setCcTypeAccountId(Integer ccTypeAccountId) {
		this.ccTypeAccountId = ccTypeAccountId;
	}

	public String getCcTypeAccountName() {
		return this.ccTypeAccountName;
	}

	public void setCcTypeAccountName(String ccTypeAccountName) {
		this.ccTypeAccountName = ccTypeAccountName;
	}

	public List<AccountTypeDetail> getAccountTypeDetails() {
		return this.accountTypeDetails;
	}

	public void setAccountTypeDetails(List<AccountTypeDetail> accountTypeDetails) {
		this.accountTypeDetails = accountTypeDetails;
	}

	public AccountTypeDetail addAccountTypeDetail(AccountTypeDetail accountTypeDetail) {
		getAccountTypeDetails().add(accountTypeDetail);
		accountTypeDetail.setTypeAccount(this);

		return accountTypeDetail;
	}

	public AccountTypeDetail removeAccountTypeDetail(AccountTypeDetail accountTypeDetail) {
		getAccountTypeDetails().remove(accountTypeDetail);
		accountTypeDetail.setTypeAccount(null);

		return accountTypeDetail;
	}

}