package com.example.todo.domain.repository.authority;

import java.util.List;

import com.example.todo.domain.model.AuthorityForInsert;
import com.example.todo.domain.model.AuthorityImpl;

public interface AuthorityRepository {

	List<AuthorityImpl> findByAccountId(String accountId);
	
	void insert(AuthorityForInsert authority);

}
