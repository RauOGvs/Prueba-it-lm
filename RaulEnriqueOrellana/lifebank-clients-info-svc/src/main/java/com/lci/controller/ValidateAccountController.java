package com.lci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lci.process.ValidateAccountProcess;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.AccountClientPojo;
import com.lci.service.pojo.response.ClientAccountResponse;

@RestController
@RequestMapping("/account")
public class ValidateAccountController {

	private ValidateAccountProcess process;

	public ValidateAccountController(ValidateAccountProcess process) {
		this.process = process;
	}

	@GetMapping("/validateAccountType/{accountID}")
	public ValueResponse<ClientAccountResponse> validateAccounts(@PathVariable("accountID") String accountID) {

		return process.validateAccountProcess(accountID);
	}

}
