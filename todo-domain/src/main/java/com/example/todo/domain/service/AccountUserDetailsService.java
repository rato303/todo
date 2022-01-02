package com.example.todo.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.Account;
import com.example.todo.domain.model.AccountUserDetails;
import com.example.todo.domain.repository.account.AccountRepository;

@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {

	AccountRepository accountRepository;

	AccountUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByName(username);
		return new AccountUserDetails(account);
	}

}
