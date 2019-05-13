package com.lci.service.pojo.response;

public class ClientAccountResponse {

	private String accountID;
	private String clientID;
	private String typeAccount;
	private int typeAccountInt;
	private double amount;
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTypeAccountInt() {
		return typeAccountInt;
	}
	public void setTypeAccountInt(int typeAccountInt) {
		this.typeAccountInt = typeAccountInt;
	}
	
}
