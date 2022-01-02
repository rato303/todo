package com.example.todo.domain.service;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.domain.model.AccountForInsert;
import com.example.todo.domain.service.AccountCreateService.AccountForCreate;

@Service
public class AccountForInsertFactoryImpl implements AccountForInsertFactory {
	
	PasswordEncoder passwordEncoder;
	
	@Inject
	AccountForInsertFactoryImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AccountForInsert create(AccountForCreate account) {
		String encryptedPassword = this.passwordEncoder.encode(account.getRawPassword());
		return new AccountForInsertImpl(account, encryptedPassword);
	}
	
	private class AccountForInsertImpl implements AccountForInsert {
		
		AccountForCreate account;
		String encryptedPassword;
		
		AccountForInsertImpl(AccountForCreate account, String encryptedPassword) {
			this.account = account;
			this.encryptedPassword = encryptedPassword;
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
			return encryptedPassword;
		}
		
	}


}
