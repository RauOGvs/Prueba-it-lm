package com.lci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lci.process.GetAllAccountProcess;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.AccountClientPojo;

@RestController
@RequestMapping("/account")
public class GetAccountsController {

	private GetAllAccountProcess process;

	public GetAccountsController(GetAllAccountProcess process) {
		this.process = process;
	}

	@GetMapping("/get/{idCliente}")
	public ValueResponse<AccountClientPojo> getAccounts(@PathVariable("idCliente") String idCliente) {

		return process.getAccount(idCliente);
	}

}
