package com.lba.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lba.entity.UsrUser;
import com.lba.jwt.repository.IJwt;
import com.lba.repository.GetInfoRepositoryImplementation;
import com.lba.service.getinfo.request.UserInfoRequest;

@Service
public class GetUserInfoProcess {

	private IJwt getJwt;
	private Logger log;
	private GetInfoRepositoryImplementation getInfo;
	public GetUserInfoProcess(IJwt getJwt, GetInfoRepositoryImplementation getInfo) {
		this.getJwt = getJwt;
		this.getInfo = getInfo;
		this.log = LoggerFactory.getLogger(getClass());
	}
	
	public void getInfo(UserInfoRequest request) {
		
		UsrUser user = getInfo.getInfoUser(request.getUserId());
		String jwt = getJwt.generateJwt(request.getUserId(), request.getIp());
		log.info("JWT: {}", jwt);
		
		
	}
}
