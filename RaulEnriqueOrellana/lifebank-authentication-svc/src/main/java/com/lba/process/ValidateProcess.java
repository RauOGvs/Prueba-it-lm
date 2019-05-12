package com.lba.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lba.jwt.repository.IJwt;
import com.lba.response.ValueResponse;

@Service
public class ValidateProcess {

	private IJwt ijwt;
	private Logger log;

	public ValidateProcess(IJwt ijwt) {
		this.log = LoggerFactory.getLogger(getClass());
		this.ijwt = ijwt;

	}

	public ValueResponse<Integer> validateProcess(String jwt, String ip) {

		ValueResponse<Integer> response = null;
		ValueResponse<Integer>  result = null;
		Integer val =0;
		String message = "";
		try {
			log.info("Entrada a validateJWT: jwt: {} IP: {}", jwt, ip);
			result = ijwt.validateJwt(jwt, ip);
			message = "";
			val = result.getValue();
			switch (val) {

			case 403: {
				message = "IP or signature was changed";
				break;
			}
			case 200: {
				message = result.getMessage();
				break;
			}
			case 440: {
				message = "Token has expired";
				break;
			}
			default:
				message = "NO DATA";
			}
			response = new ValueResponse<Integer>("200", val, message);
			log.info("Response validate jwt: {}", new ObjectMapper().writeValueAsString(response));
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			response = new ValueResponse<Integer>("201", val, message);
		}
		return response;
	}

}
