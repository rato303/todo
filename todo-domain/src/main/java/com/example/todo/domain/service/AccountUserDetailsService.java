package com.example.todo.domain.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.Account;
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
		return new UserDetailsImpl(account);
	}

	private class UserDetailsImpl implements UserDetails {

		private static final long serialVersionUID = 1095316793216814073L;

		Account account;

		UserDetailsImpl(Account account) {
			this.account = account;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.emptyList();
		}

		@Override
		public String getPassword() {
			return account.getPassword();
		}

		@Override
		public String getUsername() {
			return account.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			// always
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// always
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// always
			return true;
		}

		@Override
		public boolean isEnabled() {
			// always
			return true;
		}

	}

}
