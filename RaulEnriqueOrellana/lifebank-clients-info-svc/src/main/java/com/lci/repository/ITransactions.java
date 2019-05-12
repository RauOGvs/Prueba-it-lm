package com.lci.repository;

import java.util.List;

public interface ITransactions<I, T,O> {
	
	public List<O> getTransactions(I accountId, T initDate, T finalDate);
	

}
