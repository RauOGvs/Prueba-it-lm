package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cc_client_id")
	private String ccClientId;

	@Column(name="cc_client_name")
	private String ccClientName;

	//bi-directional many-to-one association to ClientAccount
	@OneToMany(mappedBy="client")
	private List<ClientAccount> clientAccounts;

	public Client() {
	}

	public String getCcClientId() {
		return this.ccClientId;
	}

	public void setCcClientId(String ccClientId) {
		this.ccClientId = ccClientId;
	}

	public String getCcClientName() {
		return this.ccClientName;
	}

	public void setCcClientName(String ccClientName) {
		this.ccClientName = ccClientName;
	}

	public List<ClientAccount> getClientAccounts() {
		return this.clientAccounts;
	}

	public void setClientAccounts(List<ClientAccount> clientAccounts) {
		this.clientAccounts = clientAccounts;
	}

	public ClientAccount addClientAccount(ClientAccount clientAccount) {
		getClientAccounts().add(clientAccount);
		clientAccount.setClient(this);

		return clientAccount;
	}

	public ClientAccount removeClientAccount(ClientAccount clientAccount) {
		getClientAccounts().remove(clientAccount);
		clientAccount.setClient(null);

		return clientAccount;
	}

}