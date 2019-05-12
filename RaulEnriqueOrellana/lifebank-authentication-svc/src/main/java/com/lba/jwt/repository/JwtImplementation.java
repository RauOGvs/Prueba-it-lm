package com.lba.jwt.repository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.lba.response.ValueResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtImplementation implements IJwt {

	private Logger log;
	private Environment env;
	public JwtImplementation(Environment env) {
		this.log = LoggerFactory.getLogger(getClass());
		this.env = env;
	}
	
	@Override
	public String generateJwt(String userId, String ip) {
		String token = Jwts.builder().setSubject(userId).claim("userId", userId).claim("ip", ip)
				.setExpiration(new Date(System.currentTimeMillis() + 600000)).signWith(SignatureAlgorithm.HS512, env.getProperty("service.jwt.signature"))
				.compact();

		return token;
	}

	@Override
	public ValueResponse<Integer> validateJwt(String jwt, String ipAdd) {
		/*
		 * 403 = firma cambiada
		 * 440 = expirado
		 * 403 = ip invalida
		 * 200 = OK
		 * */
		String cliendID = "";
		try {
			Claims claim = (Claims) Jwts.parser().setSigningKey(env.getProperty("service.jwt.signature")).parse(jwt).getBody();
			String ip = claim.get("ip").toString();
			cliendID = claim.get("userId").toString();
			if(!ip.equals(ipAdd)) {
				log.error("IP doesn't match, error: {}", 403);
				return new ValueResponse<>("200", 403, "");
			}
		} catch (SignatureException e) {
			log.error("Signature doesn't match, error: {}", 403);
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return new ValueResponse<>("200", 403, "");
		}catch(ExpiredJwtException e){
			log.error("Expiration date, error: {}", 440);
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return new ValueResponse<>("200", 440, "");
		}
		log.error("validate SUCCESS: {}", 200);
		return new ValueResponse<>("200", 200, cliendID);
	}

}
