package com.lba.repository;

import org.springframework.data.repository.CrudRepository;

import com.lba.entity.UsrUser;

public interface IGetInfo extends CrudRepository<UsrUser, String> {

}
