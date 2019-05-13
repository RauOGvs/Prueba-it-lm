package com.lci.service.pojo.request;

public class TransferAccountRequest {

	private String accountIdOrigin;
	private String accountIdDestination;
	private Double amount;
	private String clientId;
	private Integer typeAccountOrigin;
	private Integer typeAccountDestinations;
	private Double amountOrigin; 
	private Double amountDestination;
	
	public TransferAccountRequest(String accountIdOrigin, String accountIdDestination, Double amount, String clientId,
			Integer typeAccountOrigin, Integer typeAccountDestinations, Double amountOrigin, Double amountDestination) {
		super();
		this.accountIdOrigin = accountIdOrigin;
		this.accountIdDestination = accountIdDestination;
		this.amount = amount;
		this.clientId = clientId;
		this.typeAccountOrigin = typeAccountOrigin;
		this.typeAccountDestinations = typeAccountDestinations;
		this.amountOrigin = amountOrigin;
		this.amountDestination = amountDestination;
	}

	public TransferAccountRequest() {
	}
	
	public Integer getTypeAccountOrigin() {
		return typeAccountOrigin;
	}

	public void setTypeAccountOrigin(Integer typeAccountOrigin) {
		this.typeAccountOrigin = typeAccountOrigin;
	}

	public Integer getTypeAccountDestinations() {
		return typeAccountDestinations;
	}

	public void setTypeAccountDestinations(Integer typeAccountDestinations) {
		this.typeAccountDestinations = typeAccountDestinations;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setAmountOrigin(Double amountOrigin) {
		this.amountOrigin = amountOrigin;
	}

	public void setAmountDestination(Double amountDestination) {
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