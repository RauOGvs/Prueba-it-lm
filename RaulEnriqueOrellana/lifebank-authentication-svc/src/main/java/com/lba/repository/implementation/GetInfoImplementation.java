package com.lba.repository.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lba.entity.UsrUser;
import com.lba.repository.IGetInfo;

@Component
public class GetInfoImplementation {

	private IGetInfo getInfo;
	private Logger log;

	public GetInfoImplementation(IGetInfo getInfo) {
		this.log = LoggerFactory.getLogger(getClass());
		this.getInfo = getInfo;
	}

	public UsrUser getInfoUser(String userId) {
		try {
			Optional<UsrUser> userBd = getInfo.findById(userId);
			if (userBd.isPresent()) {
				return userBd.get();
			}
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return null;

	}

	public boolean updateStatus(UsrUser user) {

		return getInfo.save(user) != null ? true : false;

	}

	public boolean save(UsrUser user) {

		return getInfo.save(user) != null ? true : false;

	}
}
