package com.lbint.repository;

import com.lbint.response.repository.ValueResponse;

public interface IValidation {

	public boolean validateHeaderEmpty(String header);

	public ValueResponse<Integer> isValidaToken(String header, String ip);

}
