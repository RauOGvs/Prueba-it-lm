package com.lba;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lba.jwt.repository.IJwt;
import com.lba.process.GetUserInfoProcess;
import com.lba.repository.GetInfoRepositoryImplementation;
import com.lba.service.getinfo.request.UserInfoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetUserInfoTest {
	
	private GetUserInfoProcess process;
	@Autowired
	private IJwt jwt;
	@Autowired
	private GetInfoRepositoryImplementation getInfo;
	@Test
	public void GetUserInfo() {
		
		process = new GetUserInfoProcess(jwt, getInfo);
		UserInfoRequest request = new UserInfoRequest();
		request.setIntentos(0);
		request.setIp("0.8.8.8");
		request.setUserId("rorellana");
		request.setPassword("asdasda");
		process.getInfo(request);
		
	}

}
