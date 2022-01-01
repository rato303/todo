package com.example.todo.app;

public class AccountForm {

	private String name;
	
	private String password;
	
	public AccountForm() {
		this.name = "";
		this.password = "";
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
	
}
