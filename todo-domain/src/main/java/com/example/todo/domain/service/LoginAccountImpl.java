package com.example.todo.domain.service;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.todo.domain.model.Account;

@Component
@Scope("application")
public class LoginAccountImpl implements Serializable, LoginAccount {

	private static final long serialVersionUID = 8282978032457290079L;
	
	private Account account;
	
	@Override
	public String getFullName() {
		if (null == this.account) {
			return "";
		}
		return this.account.getLastName() + " " + this.account.getFirstName();
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
