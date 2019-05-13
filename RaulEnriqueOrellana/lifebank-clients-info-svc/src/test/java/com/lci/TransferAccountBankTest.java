package com.lci;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lci.process.TransferAccountBankProcess;
import com.lci.service.pojo.request.TransferAccountRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferAccountBankTest {

	@Autowired
	private TransferAccountBankProcess process;

	@Test
	public void transferBank() {

		try {
			process.transferAccountProcess(new TransferAccountRequest("048360533-2", "048360533-1", new Double(25),
					"048360533", 5, 6, new Double(600), new Double(700)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
