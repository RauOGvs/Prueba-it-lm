package com.lbint.service.pojo.request;

public class TransferAccountRequest {

	private String accountOrigin;
	private String accountDestination;
	private double accountAmount;
	private String ip;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
