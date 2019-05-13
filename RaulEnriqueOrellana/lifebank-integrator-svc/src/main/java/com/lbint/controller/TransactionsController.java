package com.lbint.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbint.process.GetAccountProcess;
import com.lbint.process.TransactionProcess;

@RestController
@RequestMapping("/integrator")
public class TransactionsController {

	private TransactionProcess process;

	public TransactionsController(TransactionProcess process) {

		this.process = process;

	}

	@GetMapping("/transaction/{ip}/{accountID}")
	public ResponseEntity<?> getAccounts(@PathVariable("ip") String ip, @PathVariable("accountID") String accountID,
			@PathParam(value="start") String start, @PathParam(value="end") String end,
			@RequestHeader(value = "Authorization", required = true) String header) {

		return process.process(header, accountID, start, end, ip);
	}

}
