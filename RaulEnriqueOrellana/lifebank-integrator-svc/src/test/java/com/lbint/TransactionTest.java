package com.lbint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbint.process.TransactionProcess;
import com.lbint.service.pojo.response.AccountTransactionPojo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

	@Autowired
	private TransactionProcess process;

	@Test
	public void TransactionTst() {
		mock();
		
		Assert.assertNotNull(process.process("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNDgzNjA1MzMiLCJ1c2VySWQiOiIwNDgzNjA1MzMiLCJpcCI6IjguOC44LjgiLCJleHAiOjE1NTc2NDUyMTB9.xIEkS_UYheplPjI8ocVotimq3eHOE4iyu1NQlIWbwMPdeUoRvkwZmIWAjo2MR-8hG0X74rO20wl3uHKT1UUNkA", "048360533-1", "2019-04-01", "2019-05-19", "8.8.8.8"));
	}

	private void mock() {
		
		String response = "{ 	\"id\": \"048360533-1\", 	\"startDate\": \"2019-05-01\", 	\"endDate\": \"2019-05-19\", 	\"transactions\": [{ 			\"id\": \"PG-123-08\", 			\"date\": \"2019-05-19 00:00:00.0\", 			\"description\": \"compra gasolina\", 			\"amount\": \"45\" 		}, { 			\"id\": \"PG-123-10\", 			\"date\": \"2019-05-18 00:00:00.0\", 			\"description\": \"compra gasolina\", 			\"amount\": \"34\" 		}, { 			\"id\": \"PG-123-09\", 			\"date\": \"2019-05-01 00:00:00.0\", 			\"description\": \"compra gasolina\", 			\"amount\": \"12\" 		} 	] }";
		try{
			ObjectMapper mapper = new ObjectMapper();
			AccountTransactionPojo ac = mapper.readValue(response, AccountTransactionPojo.class);
			when(process.process(any(), any(), any(), any(), any())).thenReturn(new ResponseEntity(ac, HttpStatus.OK));
		}catch(Exception e) {
			
		}
	}

}
