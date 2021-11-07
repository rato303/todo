package com.example.todo.domain.service;

import java.util.Collection;

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
import com.example.todo.domain.repository.account.AccountRepository;

@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {
	
    @Inject
    AccountRepository accountRepository;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

    	Account account = accountRepository.findOneByUsername(username);
    	return new AccountUserDetails(account, getAuthorities(account));
    }

    private Collection<GrantedAuthority> getAuthorities(Account account) {
        if (account.isAdmin()) {
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }

}
