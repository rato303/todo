package com.example.todo.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.Account;
import com.example.todo.domain.model.AccountUserDetails;
import com.example.todo.domain.model.AuthorityImpl;
import com.example.todo.domain.repository.account.AccountRepository;
import com.example.todo.domain.repository.authority.AuthorityRepository;

@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {

	AccountRepository accountRepository;
	AuthorityRepository authorityRepository;

	@Inject
	AccountUserDetailsService(AccountRepository accountRepository, AuthorityRepository authorityRepository) {
		this.accountRepository = accountRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByName(username);
		List<AuthorityImpl> authorities = authorityRepository.findByAccountId(account.getId());
		List<String> stringAauthorities = authorities.stream().map(AuthorityImpl::getName).collect(Collectors.toList());
		List<GrantedAuthority> granteAauthorities = AuthorityUtils.createAuthorityList(stringAauthorities.toArray(new String[stringAauthorities.size()]));
		return new AccountUserDetails(account, granteAauthorities);
	}

}
