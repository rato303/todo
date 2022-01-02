package com.example.todo.app;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.todo.domain.model.Authority;
import com.example.todo.domain.service.AccountCreateService.AccountForCreate;

public class AccountForm {

	private String name;
	
	private String password;
	
	private String authorities;

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
	
	public String getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	public List<Authority> asAuthorities() {
		String[] split = this.authorities.split(",");
		return Arrays.stream(split)
				.map((v) -> {
					return Authority.stringOf(v);
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return "AccountForm [name=" + name + ", password=" + password + ", authorities=" + authorities + "]";
	}

	private class AccountForCreateImpl implements AccountForCreate {
		
		AccountForm form;
		List<Authority> authorities;
		
		AccountForCreateImpl(AccountForm form) {
			this.form = form;
			this.authorities = form.asAuthorities();
		}

		@Override
		public String getId() {
			UUID uuid = UUID.randomUUID();
			return uuid.toString();
		}

		@Override
		public String getName() {
			return form.getName();
		}

		@Override
		public String getRawPassword() {
			return form.getPassword();
		}

		@Override
		public List<Authority> getAuthorities() {
			return authorities;
		}
		
	}
	
}
