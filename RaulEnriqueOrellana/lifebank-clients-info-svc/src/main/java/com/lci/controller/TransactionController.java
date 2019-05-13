package com.lci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lci.process.GetTransactionClientProcess;
import com.lci.response.ValueResponse;
import com.lci.service.pojo.response.AccountTransactionPojo;

@RestController
@RequestMapping("/account")
public class TransactionController {

	private GetTransactionClientProcess process;

	public TransactionController(GetTransactionClientProcess process) {
		this.process = process;
	}

	@GetMapping("/transaction/{accountID}/{startDate}/{endDate}")
	public ValueResponse<AccountTransactionPojo> transactions(@PathVariable("accountID") String accountID,
			@PathVariable("startDate") String initDate, @PathVariable("endDate") String endDate) {

		return process.process(accountID, initDate, endDate);

	}

}
