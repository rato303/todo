package com.example.todo.domain.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.AccountForInsert;
import com.example.todo.domain.repository.account.AccountRepository;

@Service
@Transactional
public class AccountCreateServiceImpl implements AccountCreateService {
	
	AccountRepository accountRepository;
	AccountForInsertFactory accountForInsertFactory;
	
	@Inject
	AccountCreateServiceImpl(AccountRepository accountRepository, AccountForInsertFactory accountForInsertFactory) {
		this.accountRepository = accountRepository;
		this.accountForInsertFactory = accountForInsertFactory;
	}

	@Override
	public void create(AccountForCreate account) {
		AccountForInsert accountForInsert = accountForInsertFactory.create(account);
		accountRepository.insert(accountForInsert);
	}
	
}
