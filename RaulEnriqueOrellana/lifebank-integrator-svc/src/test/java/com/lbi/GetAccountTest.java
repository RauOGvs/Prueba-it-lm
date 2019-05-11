package com.lbi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbint.process.GetAccountProcess;
import com.lbint.repository.IRestAPI;
import com.lbint.repository.IValidation;
import com.lbint.repository.implementation.ValidationImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAccountTest {
	
	@Autowired
	private GetAccountProcess process;
	@Autowired
	private IValidation validations;
	@Autowired
	private IRestAPI restAPI;
	@Autowired
	private Environment env;
	
	
	@Test
	public void getAccount() {
		process.getAccountData("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb3JlbGxhbmEiLCJ1c2VySWQiOiJyb3JlbGxhbmEiLCJpcCI6IjguOC44LjgiLCJleHAiOjE1NTc1OTMwMTh9.IuexxSqm1qCm9UJ0ufo_FPGsKLCTXS5it4ye-VX2Hw_fzeWdPY0-cbfEjMTvKZcnY1M1CrDZ85vVxiE-wi0qtA", "8.8.8.8", "048360533");
		
	}

}
