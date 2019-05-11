package com.lba.repository;

import org.springframework.data.repository.CrudRepository;

import com.lba.entity.UsrUserSession;

public interface IGetInfoSession extends CrudRepository<UsrUserSession, Integer> {
	

}
