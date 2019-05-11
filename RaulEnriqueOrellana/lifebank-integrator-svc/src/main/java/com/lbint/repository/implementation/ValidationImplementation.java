package com.lbint.repository.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbint.repository.IValidation;
import com.lbint.response.repository.ValueResponse;
import com.lbint.utility.RestAPI;

@Component
public class ValidationImplementation implements IValidation {

	private Logger log;
	private RestAPI restAPI;
	private Environment env;

	public ValidationImplementation(RestAPI restAPI, Environment env) {
		this.log = LoggerFactory.getLogger(getClass());
		this.restAPI = restAPI;
		this.env = env;
	}

	@Override
	public boolean validateHeaderEmpty(String header) {
		if ("".equals(header)) {
			log.error("RESPONSE: {}", HttpStatus.UNAUTHORIZED);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Integer isValidaToken(String header, String ip) {
		try {
			String[] headers = header.split(" ");
			String jwt = headers[1];
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.validate-jwt"))
			.append("/")
			.append(jwt)
			.append("/")
			.append(ip);
			log.info("URL: {}", url.toString());
			ValueResponse<Integer> response = restAPI.call(url.toString(), HttpMethod.GET,
					null, new ParameterizedTypeReference<ValueResponse<Integer>>() {
					});
			log.info("Response validation of jwt: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".contentEquals(response.getResponseCode())) {
				return response.getValue();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;

	}

}
