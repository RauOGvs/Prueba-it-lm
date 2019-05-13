package com.lci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lci.process.ValidateAccountProcess;
import com.lci.process.ValidateAccountTypeProcess;
import com.lci.response.ValueResponse;

@RestController
@RequestMapping("/account")
public class ValidateAccountTypeController {

	private ValidateAccountTypeProcess process;

	public ValidateAccountTypeController(ValidateAccountTypeProcess process) {
		this.process = process;
	}

	@GetMapping("/validateAccount/{accountID}")
	public ValueResponse<String> validateAccountsType(@PathVariable("accountID") String accountID) {

		return process.validateAccountProcess(accountID);
	}

}
