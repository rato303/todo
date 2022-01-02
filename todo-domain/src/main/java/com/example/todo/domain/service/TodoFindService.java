package com.example.todo.domain.service;

import com.example.todo.domain.model.FindByAccountIdForTodo;

public interface TodoFindService {

	TodoList find(FindByAccountIdForTodo params);
	
}
