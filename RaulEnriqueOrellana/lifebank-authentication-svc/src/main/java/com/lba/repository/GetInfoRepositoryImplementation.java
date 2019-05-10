package com.lba.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lba.entity.UsrUser;

@Component
public class GetInfoRepositoryImplementation {

	private IGetInfo getInfo;
	private Logger log;

	public GetInfoRepositoryImplementation(IGetInfo getInfo) {
		this.log = LoggerFactory.getLogger(getClass());
		this.getInfo = getInfo;
	}

	public UsrUser getInfoUser(String userId) {
		try {
			Optional<UsrUser> userBd = getInfo.findById(userId);
			return userBd.get();
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e, e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;

	}
}
