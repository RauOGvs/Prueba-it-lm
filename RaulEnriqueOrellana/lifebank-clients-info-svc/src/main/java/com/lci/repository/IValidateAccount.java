package com.lci.repository;

import org.springframework.data.repository.CrudRepository;

import com.lci.entity.ClientAccount;

public interface IValidateAccount extends CrudRepository<ClientAccount, String> {

}
