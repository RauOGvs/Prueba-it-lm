package com.lbint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbint.process.GetAccountProcess;

@RestController
@RequestMapping("/integrator")
public class GetAccountsClientController {

	private GetAccountProcess process;

	public GetAccountsClientController(GetAccountProcess process) {

		this.process = process;

	}

	@GetMapping("/myAccounts/{ip}/{clientId}")
	public ResponseEntity<?> getAccounts(@PathVariable("ip") String ip, @PathVariable("clientId") String clientId,
			@RequestHeader(value = "Authorization", required = true) String header) {

		return process.getAccountData(header, ip, clientId);
	}

}
