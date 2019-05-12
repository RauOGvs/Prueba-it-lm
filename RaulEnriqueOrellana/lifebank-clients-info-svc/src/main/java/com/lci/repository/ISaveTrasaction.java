package com.lci.repository;

import org.springframework.data.repository.CrudRepository;

import com.lci.entity.AccountTrasaction;

public interface ISaveTrasaction extends CrudRepository<AccountTrasaction, String> {

}
