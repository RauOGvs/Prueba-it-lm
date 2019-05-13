package com.lbint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbint.process.TransferBankProcess;
import com.lbint.service.pojo.request.TransferAccountRequest;

@RestController
@RequestMapping("/integrator")
public class TransferController {

	private TransferBankProcess process;

	public TransferController(TransferBankProcess process) {
		this.process = process;
	}

	@PostMapping("/transfer")
	public ResponseEntity<?> transfers(@RequestBody TransferAccountRequest request,
			@RequestHeader(value = "Authorization", required=true) String header) {

			return process.process(request, header);
	}
}
