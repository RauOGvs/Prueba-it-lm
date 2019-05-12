package com.lci.service.pojo.request;

public class TransferAccountRequest {

	private String accountIdOrigin;
	private String accountIdDestination;
	private double amount;
	private String clientId;
	private Integer typeAccount;
	private double amountOrigin; 
	private double amountDestination;
	
	
	
	public TransferAccountRequest(String accountIdOrigin, String accountIdDestination, double amount, String clientId,
			Integer typeAccount, double amountOrigin, double amountDestination) {
		super();
		this.accountIdOrigin = accountIdOrigin;
		this.accountIdDestination = accountIdDestination;
		this.amount = amount;
		this.clientId = clientId;
		this.typeAccount = typeAccount;
		this.amountOrigin = amountOrigin;
		this.amountDestination = amountDestination;
	}
	public String getAccountIdOrigin() {
		return accountIdOrigin;
	}
	public void setAccountIdOrigin(String accountIdOrigin) {
		this.accountIdOrigin = accountIdOrigin;
	}
	public String getAccountIdDestination() {
		return accountIdDestination;
	}
	public void setAccountIdDestination(String accountIdDestination) {
		this.accountIdDestination = accountIdDestination;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Integer getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(Integer typeAccount) {
		this.typeAccount = typeAccount;
	}
	public double getAmountOrigin() {
		return amountOrigin;
	}
	public void setAmountOrigin(double amountOrigin) {
		this.amountOrigin = amountOrigin;
	}
	public double getAmountDestination() {
		return amountDestination;
	}
	public void setAmountDestination(double amountDestination) {
		this.amountDestination = amountDestination;
	}
	
	
}
