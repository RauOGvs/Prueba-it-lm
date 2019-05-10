package com.lba.jwt.repository;

import java.util.Date;

import javax.security.auth.message.config.AuthConfig;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

		Claims claim;
		
		claim = (Claims) Jwts.parser()
				.setSigningKey("hola")
				.parse(jwt)
				.getBody();
		
		claim.get("exp");
		claim.get("userId");
		return false;
	}

}
