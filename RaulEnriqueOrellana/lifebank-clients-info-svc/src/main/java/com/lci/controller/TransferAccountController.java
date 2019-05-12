package com.lci.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lci.process.TransferAccountBankProcess;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.request.TransferAccountRequest;

@RestController
@RequestMapping("/account")
public class TransferAccountController {

	private TransferAccountBankProcess process;
	private Logger log;
	public TransferAccountController(TransferAccountBankProcess process) {
		this.process = process;
		this.log = LoggerFactory.getLogger(getClass());
	}
	
	@PostMapping("/doTransfer")
	public ValueResponse<String> transfers (@RequestBody TransferAccountRequest request){
		
		ValueResponse<String> response;
		try {
			log.info("Request: {}", new ObjectMapper().writeValueAsString(request));
			String auth = process.transferAccountProcess(request);
			if(auth!=null) {
				response = new ValueResponse<>("200", auth, "SUCCESS");
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return response;
			}else {
				response = new ValueResponse<>("201", auth, "NO AUTH");
				log.info("response: {}", new ObjectMapper().writeValueAsString(response));
				return response;
			}
		} catch (Exception e) {
			response = new ValueResponse<>("201", null, "NO DATA");
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return response;
		}
	
	}
}
