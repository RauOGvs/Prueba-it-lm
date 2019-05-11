package com.lba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lba.process.GetUserInfoProcess;
import com.lba.process.ValidateProcess;
import com.lba.response.ValueResponse;
import com.lba.service.getinfo.request.UserInfoRequest;

@RestController
@RequestMapping("/auth")
public class ValidateJwtController {

	private ValidateProcess process;
	public ValidateJwtController(ValidateProcess process) {
		this.process = process;
	}
	
	@GetMapping("/validate/{jwt}/{ip}")
	public ValueResponse<Integer> getJwt(@PathVariable("jwt") String jwt, @PathVariable("ip") String ip){
		
		return process.validateProcess(jwt, ip);
		
	}
}
