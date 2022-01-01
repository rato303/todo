package com.example.todo.domain.repository.account;

import java.util.List;

import com.example.todo.domain.model.Account;
import com.example.todo.domain.model.AccountForInsert;

public interface AccountRepository {
	
	List<Account> findAll();

	void insert(AccountForInsert account);
	
}
