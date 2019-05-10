package com.lba.jwt.repository;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtImplementation implements IJwt {

	@Override
	public String generateJwt(String userId, String ip) {
		String token = Jwts
				.builder()
				.setSubject(userId)
				.claim("userId", userId)
				.claim("ip", ip)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						"hola").compact();

		return token;
	}

	@Override
	public boolean validateJwt(String jwt) {
		// TODO Auto-generated method stub
		return false;
	}

}
