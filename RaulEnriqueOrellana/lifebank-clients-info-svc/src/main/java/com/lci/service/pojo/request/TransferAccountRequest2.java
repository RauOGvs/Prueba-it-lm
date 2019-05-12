package com.lci.service.pojo.request;

public class TransferAccountRequest2 {

	private String accountOrigin;
	private String accountDestination;
	private double accountAmount;
	public String getAccountOrigin() {
		return accountOrigin;
	}
	public void setAccountOrigin(String accountOrigin) {
		this.accountOrigin = accountOrigin;
	}
	public String getAccountDestination() {
		return accountDestination;
	}
	public void setAccountDestination(String accountDestination) {
		this.accountDestination = accountDestination;
	}
	public double getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}
	
	
}
