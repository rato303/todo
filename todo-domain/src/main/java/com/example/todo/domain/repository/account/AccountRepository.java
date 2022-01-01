package com.example.todo.domain.repository.account;

import java.util.List;

import com.example.todo.domain.model.Account;

public interface AccountRepository {
	
	List<Account> findAll();

}
