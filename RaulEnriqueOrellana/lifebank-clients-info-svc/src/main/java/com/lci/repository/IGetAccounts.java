package com.lci.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lci.entity.Client;
import com.lci.entity.ClientAccount;

public interface IGetAccounts extends CrudRepository<ClientAccount, String> {

	public List<ClientAccount> findByClient(Client client);
}
