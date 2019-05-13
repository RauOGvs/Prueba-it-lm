package com.lba.jwt.repository;

import com.lba.response.ValueResponse;

public interface IJwt {
	
	public String generateJwt(String userId, String ip);
	public ValueResponse<Integer> validateJwt(String jwt, String ip);

}
