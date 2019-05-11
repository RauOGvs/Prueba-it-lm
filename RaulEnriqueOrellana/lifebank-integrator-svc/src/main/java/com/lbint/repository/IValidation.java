package com.lbint.repository;

public interface IValidation {

	public boolean validateHeaderEmpty(String header);

	public Integer isValidaToken(String header, String ip);

}
