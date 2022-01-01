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
	
	@Inject
	AccountCreateServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void create(AccountForCreate account) {
		AccountForInsertImpl accountForInsert = new AccountForInsertImpl(account);
		accountRepository.insert(accountForInsert);
	}
	
	private class AccountForInsertImpl implements AccountForInsert {
		
		AccountForCreate account;
		
		AccountForInsertImpl(AccountForCreate account) {
			this.account = account;
		}

		@Override
		public String getId() {
			return account.getId();
		}

		@Override
		public String getName() {
			return account.getName();
		}

		@Override
		public String getPassword() {
			return account.getPassword();
		}
		
	}

}
