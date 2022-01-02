package com.example.todo.domain.service;

import com.example.todo.domain.model.AccountForInsert;
import com.example.todo.domain.service.AccountCreateService.AccountForCreate;

public interface AccountForInsertFactory {
	
	AccountForInsert create(AccountForCreate account);

}
