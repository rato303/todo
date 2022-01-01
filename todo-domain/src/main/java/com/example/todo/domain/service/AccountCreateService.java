package com.example.todo.domain.service;

public interface AccountCreateService {

	void create(AccountForCreate account);
	
	public interface AccountForCreate {
		
		String getId();
		
		String getName();
		
		String getPassword();
		
	}
	
}
