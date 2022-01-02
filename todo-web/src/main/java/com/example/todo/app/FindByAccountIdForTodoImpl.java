package com.example.todo.app;

import org.springframework.data.domain.Pageable;

import com.example.todo.domain.model.AccountUserDetails;
import com.example.todo.domain.model.FindByAccountIdForTodo;

class FindByAccountIdForTodoImpl implements FindByAccountIdForTodo {
	
	AccountUserDetails accountUserDetails;
	Pageable pageable;
	
	FindByAccountIdForTodoImpl(AccountUserDetails accountUserDetails, Pageable pageable) {
		this.accountUserDetails = accountUserDetails;
		this.pageable = pageable;
	}

	@Override
	public String getAccountId() {
		return accountUserDetails.getId();
	}

	@Override
	public int getPageSize() {
		return pageable.getPageSize();
	}

	@Override
	public int getOffset() {
		return pageable.getPageNumber();
	}

}
