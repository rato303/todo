package com.example.todo.app;

import org.apache.commons.lang3.RandomStringUtils;

import com.example.todo.domain.service.AccountCreateService.AccountForCreate;

public class AccountForm {

	private String name;
	
	private String password;
	
	public AccountForm() {
		this.name = "";
		this.password = "";
	}
	
	public AccountForCreate toAccountForCreate() {
		return new AccountForCreateImpl(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountForm [name=" + name + ", password=" + password + "]";
	}
	
	private class AccountForCreateImpl implements AccountForCreate {
		
		AccountForm form;
		
		AccountForCreateImpl(AccountForm form) {
			this.form = form;
		}

		@Override
		public String getId() {
			// TODO とりあえずの実装
			return RandomStringUtils.random(36);
		}

		@Override
		public String getName() {
			return form.getName();
		}

		@Override
		public String getRawPassword() {
			return form.getPassword();
		}
		
	}
	
}
