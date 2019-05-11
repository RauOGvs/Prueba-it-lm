package com.lba.response;

public class ValueResponse<O> implements IResponse<O> {

	private String responseCode;
	private O value;
	private String message;

	public ValueResponse(String responseCode, O value, String message) {
		this.responseCode = responseCode;
		this.value = value;
		this.message = message;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public O getValue() {
		return value;
	}

	public void setValue(O value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
