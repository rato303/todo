package com.example.todo.domain.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.AccountForInsert;
import com.example.todo.domain.model.Authority;
import com.example.todo.domain.model.AuthorityForInsert;
import com.example.todo.domain.repository.account.AccountRepository;
import com.example.todo.domain.repository.authority.AuthorityRepository;

@Service
@Transactional
public class AccountCreateServiceImpl implements AccountCreateService {

	AccountRepository accountRepository;
	AuthorityRepository authorityRepository;
	AccountForInsertFactory accountForInsertFactory;

	@Inject
	AccountCreateServiceImpl(AccountRepository accountRepository, AuthorityRepository authorityRepository,
			AccountForInsertFactory accountForInsertFactory) {
		this.accountRepository = accountRepository;
		this.authorityRepository = authorityRepository;
		this.accountForInsertFactory = accountForInsertFactory;
	}

	@Override
	public void create(AccountForCreate account) {
		AccountForInsert accountForInsert = accountForInsertFactory.create(account);
		accountRepository.insert(accountForInsert);
		
		String accountId = accountForInsert.getId();
		account.getAuthorities().stream().forEach((a) -> {
			authorityRepository.insert(new AuthorityForInsertImpl(accountId, a));
		});
	}
	
	private class AuthorityForInsertImpl implements AuthorityForInsert {
		
		String accountId;
		Authority authority;
		
		AuthorityForInsertImpl(String accountId, Authority authority) {
			this.accountId = accountId;
			this.authority = authority;
		}

		@Override
		public String getAccountId() {
			return accountId;
		}

		@Override
		public String getName() {
			return authority.getValue();
		}
		
	}

}
