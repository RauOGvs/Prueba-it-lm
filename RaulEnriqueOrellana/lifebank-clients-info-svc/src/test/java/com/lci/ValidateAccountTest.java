package com.lci;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.process.ValidateAccountProcess;
import com.lci.repository.implementation.ValidateAccountImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateAccountTest {

	private ValidateAccountProcess process;
	
	@Autowired
	private ValidateAccountImplementation validate;
	@Before
	public void createMock() {
		
//		this.validate = Mockito.mock(ValidateAccountImplementation.class);
		
	}
	
	@Test
	public void getAllAccounts() {
		mock();
		Client client = new Client();
		client.setCcClientId("048360533");
		process= new ValidateAccountProcess(validate);
		
		Assert.assertNotNull(process.validateAccountProcess("048360533-1"));
	}

	private void mock() {

		ClientAccount ca = new ClientAccount();
//		when(igetAccounts.getAllAccounts(any())).thenReturn(null);
		
	}

}
