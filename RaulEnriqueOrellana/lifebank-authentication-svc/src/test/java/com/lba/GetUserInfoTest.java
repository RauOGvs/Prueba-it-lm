package com.lba;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lba.entity.UsrUser;
import com.lba.entity.UsrUserSession;
import com.lba.jwt.repository.IJwt;
import com.lba.process.GetUserInfoProcess;
import com.lba.repository.IGetInfo;
import com.lba.repository.IGetInfoSession;
import com.lba.repository.IGetSessions;
import com.lba.repository.implementation.GetInfoImplementation;
import com.lba.repository.implementation.GetInfoSessionImplementation;
import com.lba.service.getinfo.request.UserInfoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetUserInfoTest {

	
	@Autowired
	private IJwt jwt;
	private static Logger log = LoggerFactory.getLogger(GetUserInfoTest.class);
	@Autowired
	private GetInfoImplementation getInfo;
	@Autowired
	private GetInfoSessionImplementation getInfoSession;
	
	private IGetInfo getInfoBd;
	private IGetInfoSession getInfoSessionBd;
	private IGetSessions getSessionBd;

	@Before
	public void initMocks() {
		this.getInfoBd = Mockito.mock(IGetInfo.class);
		this.getInfoSessionBd = Mockito.mock(IGetInfoSession.class);
		this.getSessionBd = Mockito.mock(IGetSessions.class);
	}

	@Test
	public void GetUserInfo() {
		mock();
		UserInfoRequest request = new UserInfoRequest();
		GetUserInfoProcess process = new GetUserInfoProcess(jwt, getInfo, getInfoSession, getSessionBd);
		try {

			request.setIp("0.8.8.8");
			request.setUserId("rorellana");
			request.setPassword("c0nexion1$");
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}

		Assert.assertNotNull(process.getInfo(request));
	}

	private void mock() {
		UsrUser usr = new UsrUser();
		usr.setUsrPassword(
				"4ec9446e3512619c7487ee5c2cec1fce02ba4b222b1564c7b5bde39ea2ec13ae4e5a7d26350ee01324961f193d54548578432cb361abc4ef95860c8617dfcce3");
		usr.setUsrUserName("rorellana");
		given(this.getInfoBd.findById(any())).willReturn(null);
//		given(getSessionBd.findByUsrUserAndUusSesssionDateAndUusSessionStatus(any(), any()))
//				.willReturn((long)2);
	}

}
