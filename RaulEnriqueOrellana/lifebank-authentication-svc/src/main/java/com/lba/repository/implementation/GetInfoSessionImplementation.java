package com.lba.repository.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lba.entity.UsrUserSession;
import com.lba.repository.IGetInfoSession;

@Component
public class GetInfoSessionImplementation{

	private IGetInfoSession getInfo;
	private Logger log;

	public GetInfoSessionImplementation(IGetInfoSession getInfo) {
		this.log = LoggerFactory.getLogger(getClass());
		this.getInfo = getInfo;
	}

	public UsrUserSession getInfoUser(String userId) {
		try {
			Optional<UsrUserSession> userBd = getInfo.findById(1);
			return userBd.get();
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;

	}

	public boolean save(UsrUserSession user) {

		return getInfo.save(user) != null ? true : false;

	}

}
