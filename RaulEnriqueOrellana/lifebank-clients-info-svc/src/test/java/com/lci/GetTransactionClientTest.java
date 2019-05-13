package com.lci;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lci.entity.AccountTrasaction;
import com.lci.process.GetTransactionClientProcess;
import com.lci.repository.ITransactions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetTransactionClientTest {

	@Autowired
	private GetTransactionClientProcess process;
	@Autowired
	private ITransactions<String, Date, AccountTrasaction> itransactions;

	@Test
	public void getTransacction() {

		try {
			
			process.process("048360533-1", "2019-05-01", "2019-05-19");
		} catch (Exception e) {

		}

	}

}
