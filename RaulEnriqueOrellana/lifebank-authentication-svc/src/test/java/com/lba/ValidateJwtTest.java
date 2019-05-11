package com.lba;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.lba.jwt.repository.IJwt;
import com.lba.jwt.repository.JwtImplementation;
import com.lba.process.ValidateProcess;


public class ValidateJwtTest {
	
	private ValidateProcess process;
	private IJwt ijwt;
	private Environment env;
	@Before
	public void createMocks() {
		this.env = Mockito.mock(Environment.class);
//		this.ijwt = Mockito.mock(IJwt.class);
	}
	@Test
	public void validateJwt() {
		mock();
		ijwt = new JwtImplementation(env);
		process = new ValidateProcess(ijwt);
		process.validateProcess("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb3JlbGxhbmEiLCJ1c2VySWQiOiJyb3JlbGxhbmEiLCJpcCI6IjguMC44LjgiLCJleHAiOjE1NTc1NTkyMzF9.iHW_w9jrOaw0Z5jARh-YuHqjrU_ZDA2OH_JxFC-QzvrlMEzFTTLtqZ2rcJwM1yvA52bV4NUcQLp-nXB9z3Bl7g", "8.0.8.8");		
	}
	private void mock() {
		when(env.getProperty(any())).thenReturn("a4S5d&dTyaWb");
		
	}

}
