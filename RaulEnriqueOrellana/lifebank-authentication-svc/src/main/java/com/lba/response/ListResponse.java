package com.lba.response;

import java.util.List;

public class ListResponse<O> implements IResponse<O> {

	private String responseCode;
	private List<O> value;
	private String message;

	public ListResponse(String responseCode, List<O> value, String message) {
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

	public List<O> getValue() {
		return value;
	}

	public void setValue(List<O> value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
