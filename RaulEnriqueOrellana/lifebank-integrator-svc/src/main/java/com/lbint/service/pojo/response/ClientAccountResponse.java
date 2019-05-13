package com.lbint.service.pojo.response;

public class ClientAccountResponse {

	private String accountID;
	private String clientID;
	private String typeAccount;
	private Integer typeAccountInt;
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
	public Integer getTypeAccountInt() {
		return typeAccountInt;
	}
	public void setTypeAccountInt(Integer typeAccountInt) {
		this.typeAccountInt = typeAccountInt;
	}
	
}
