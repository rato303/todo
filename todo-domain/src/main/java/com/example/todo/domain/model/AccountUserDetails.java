package com.example.todo.domain.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1095316793216814073L;

	Account account;

	public AccountUserDetails(Account account) {
		this.account = account;
	}
	
	public String getId() {
		return account.getId();
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
