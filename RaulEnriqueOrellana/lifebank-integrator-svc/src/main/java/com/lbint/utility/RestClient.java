package com.lbint.utility;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestClientException;

public class RestClient {
	AsyncRestTemplate restTemplate;
	private Environment env;
	
	public RestClient(AsyncRestTemplate restTemplate, Environment env){
		this.restTemplate = restTemplate;
		this.env = env;
	}
		
	public <T, U> U call(String url, HttpMethod method, T requestObject, ParameterizedTypeReference<U> responseType,
			Object... uriParams) throws RestClientException, InterruptedException, ExecutionException, NumberFormatException, TimeoutException{
		HttpEntity<T> request; 
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		ListenableFuture<ResponseEntity<U>> listenableFutureResponse;
		ResponseEntity<U> response;

		headers.add("Authorization", "Basic");
		headers.add("Content-Type", "application/json");

		request = new HttpEntity<>(requestObject, headers);
		
		listenableFutureResponse = restTemplate.exchange(url, method, request, responseType, uriParams);
		
		response = listenableFutureResponse.get(Long.parseLong(env.getProperty("service.configuration.http.http-request-timeout")), TimeUnit.MILLISECONDS);

		return response.getBody();
	}
}
