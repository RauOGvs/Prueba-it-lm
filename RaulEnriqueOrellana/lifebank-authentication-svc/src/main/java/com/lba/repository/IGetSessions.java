package com.lba.repository;

public interface IGetSessions {
	
	public Long findByUsrUserAndUusSesssionDateAndUusSessionStatus(String userName, int status);

}
