package com.lba.jwt.repository;

public interface IJwt {
	
	public String generateJwt(String userId, String ip);
	public int validateJwt(String jwt, String ip);

}
