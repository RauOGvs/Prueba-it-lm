package com.lbint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbint.process.TransferBankProcess;
import com.lbint.repository.IRestAPI;
import com.lbint.repository.IValidation;
import com.lbint.service.pojo.request.TransferAccountRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferBankTest {
	
	@Autowired
	private TransferBankProcess process;
	@Autowired
	private IValidation validations;
	@Autowired
	private IRestAPI irestAPI;
	
	@Test
	public void transfer() {
		
		TransferAccountRequest request = new TransferAccountRequest();
		request.setAccountAmount(25);
		request.setAccountDestination("048360533-1");
		request.setAccountOrigin("048360533-3");
		request.setIp("8.8.8.8");
		process.process(request, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNDgzNjA1MzMiLCJ1c2VySWQiOiIwNDgzNjA1MzMiLCJpcCI6IjguOC44LjgiLCJleHAiOjE1NTc3MDg4NjN9.udmo2MCM001jAHB00srbL9oFcR1ooi81N4_jzMGFibN7SacjkO1CQaA0ZhY0lZQlLrVkbjCSfDPYin8vw4D4dA");
		
		
		
	}

}
