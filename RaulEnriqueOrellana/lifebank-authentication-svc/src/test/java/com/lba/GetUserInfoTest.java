package com.lba;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lba.entity.UsrUser;
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
	
	private GetInfoRepositoryImplementation getInfo;
	
	@Before
	public void initMocks() {
		this.getInfo = Mockito.mock(GetInfoRepositoryImplementation.class);
	}
	@Test
	public void GetUserInfo() {
		mock();
		process = new GetUserInfoProcess(jwt, getInfo);
		UserInfoRequest request = new UserInfoRequest();
		request.setIntentos(0);
		request.setIp("0.8.8.8");
		request.setUserId("rorellana");
		request.setPassword("c0nexion1");
		
		Assert.assertNotNull(process.getInfo(request));
	}
	private void mock() {
		UsrUser usr = new UsrUser();
		usr.setUsrPassword("4ec9446e3512619c7487ee5c2cec1fce02ba4b222b1564c7b5bde39ea2ec13ae4e5a7d26350ee01324961f193d54548578432cb361abc4ef95860c8617dfcce3");
		usr.setUsrUserName("rorellana");
		given(this.getInfo.getInfoUser(any())).willReturn(usr);
	}

}
