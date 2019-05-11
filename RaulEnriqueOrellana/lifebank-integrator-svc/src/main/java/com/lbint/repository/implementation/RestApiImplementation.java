package com.lbint.repository.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbint.repository.IRestAPI;
import com.lbint.response.repository.ValueResponse;
import com.lbint.service.pojo.response.AccountClientPojo;
import com.lbint.utility.RestAPI;

@Component
public class RestApiImplementation implements IRestAPI {

	private RestAPI restAPI;
	private Logger log;
	private Environment env;

	public RestApiImplementation(RestAPI restAPI, Environment env) {
		this.env = env;
		this.restAPI = restAPI;
		this.log= LoggerFactory.getLogger(getClass());
	}

	@Override
	public AccountClientPojo getAccountsClient(String clientId) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.getAccounts"))
			.append("/")
			.append(clientId);
			ValueResponse<AccountClientPojo> response = restAPI.call(url.toString(), HttpMethod.GET,
					null, new ParameterizedTypeReference<ValueResponse<AccountClientPojo>>() {
					});
			log.info("Response getAccount: {}", new ObjectMapper().writeValueAsString(response));

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
