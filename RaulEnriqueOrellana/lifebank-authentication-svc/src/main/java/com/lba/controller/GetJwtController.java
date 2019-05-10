package com.lba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lba.process.GetUserInfoProcess;
import com.lba.service.getinfo.request.UserInfoRequest;

@RestController
@RequestMapping("/auth")
public class GetJwtController {

	private GetUserInfoProcess process;
	public GetJwtController(GetUserInfoProcess process) {
		this.process = process;
	}
	
	@PostMapping("/getInfo")
	public ResponseEntity<?> getJwt(@RequestBody UserInfoRequest request){
		
		return process.getInfo(request);
		
	}
}
