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
import com.lbint.service.pojo.request.TransferAccountPojo;
import com.lbint.service.pojo.response.AccountClientPojo;
import com.lbint.service.pojo.response.AccountTransactionPojo;
import com.lbint.service.pojo.response.ClientAccountResponse;
import com.lbint.utility.RestAPI;

@Component
public class RestApiImplementation implements IRestAPI {

	private RestAPI restAPI;
	private Logger log;
	private Environment env;

	public RestApiImplementation(RestAPI restAPI, Environment env) {
		this.env = env;
		this.restAPI = restAPI;
		this.log = LoggerFactory.getLogger(getClass());
	}

	@Override
	public AccountClientPojo getAccountsClient(String clientId) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.getAccounts")).append("/").append(clientId);
			ValueResponse<AccountClientPojo> response = restAPI.call(url.toString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<ValueResponse<AccountClientPojo>>() {
					});
			log.info("Response getAccount: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".equals(response.getResponseCode())) {
				return response.getValue();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;
	}

	@Override
	public AccountTransactionPojo getTrasactions(String... param) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.transactions")).append("/").append(param[0]).append("/")
					.append(param[1]).append("/").append(param[2]);
			log.info("Request transaction: accountID: {},startDate: {}, endDate: {}", param[0], param[1], param[2]);
			ValueResponse<AccountTransactionPojo> response = restAPI.call(url.toString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<ValueResponse<AccountTransactionPojo>>() {
					});
			log.info("Response getTransaction: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".equals(response.getResponseCode())) {
				return response.getValue();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;
	}

	@Override
	public String validateAccount(String... params) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.validate-account")).append("/").append(params[0]);
			ValueResponse<String> response = restAPI.call(url.toString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<ValueResponse<String>>() {
					});
			log.info("Response validateAccount: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".equals(response.getResponseCode())) {
				return response.getValue();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;
	}

	@Override
	public ClientAccountResponse validateAccountBank(String... params) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.validate-account-type")).append(params[0]);
			ValueResponse<ClientAccountResponse> response = restAPI.call(url.toString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<ValueResponse<ClientAccountResponse>>() {
					});
			log.info("Response validateAccountType: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".equals(response.getResponseCode())
					&& env.getProperty("appPropertie.type-account").equals(response.getValue().getTypeAccount())) {
				return response.getValue();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;
	}

	@Override
	public String doTransfer(TransferAccountPojo request) {
		try {
			log.info("Request doTransfer: {}", new ObjectMapper().writeValueAsString(request));
			StringBuilder url = new StringBuilder();
			url.append(env.getProperty("service.url.transfer"));
			ValueResponse<String> response = restAPI.call(url.toString(), HttpMethod.POST, request,
					new ParameterizedTypeReference<ValueResponse<String>>() {
					});
			log.info("Response doTransfer: {}", new ObjectMapper().writeValueAsString(response));

			if (response != null && "200".equals(response.getResponseCode())) {
				return response.getValue();
			} else if (response != null && "500".equals(response.getResponseCode())) {
				return response.getResponseCode();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;
	}

}
