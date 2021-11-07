package com.example.todo.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountUserDetails implements UserDetails {

	private static final long serialVersionUID = 8435248566942117774L;
	
	private final Account account;
	private final Collection<GrantedAuthority> authorities;

	public AccountUserDetails(Account account, Collection<GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}

    public String getPassword() {
        return account.getPassword();
    }
    public String getUsername() {
        return account.getUsername();
    }
    public boolean isEnabled() {
        return true;
    }
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Account getAccount() {
        return account;
    }
    
    public String getFullName() {
    	String firstName = this.account.getFirstName();
    	String lastName = this.account.getLastName();
    	return lastName + " " + firstName;
    }

}