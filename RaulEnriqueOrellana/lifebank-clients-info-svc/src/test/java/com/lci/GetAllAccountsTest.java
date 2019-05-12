package com.lci;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lci.entity.Client;
import com.lci.entity.ClientAccount;
import com.lci.process.GetAllAccountProcess;
import com.lci.repository.implementation.GetAccountsImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAllAccountsTest {

	private GetAllAccountProcess process;

	private GetAccountsImplementation igetAccounts;
	@Before
	public void createMock() {
		
		this.igetAccounts = Mockito.mock(GetAccountsImplementation.class);
		
	}
	
	@Test
	public void getAllAccounts() {
		mock();
		Client client = new Client();
		client.setCcClientId("048360533");
		process= new GetAllAccountProcess(igetAccounts);
		
		Assert.assertNotNull(process.getAccount(client.getCcClientId()));
	}

	private void mock() {

		ClientAccount ca = new ClientAccount();
		when(igetAccounts.getAllAccounts(any())).thenReturn(null);
		
	}

}
