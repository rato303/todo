package com.example.todo.domain.repository.todo;

import java.util.List;

import com.example.todo.domain.model.FindByAccountIdForTodo;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoForInsert;

public interface TodoRepository {
	
	Todo findOne(String todoId);

	List<Todo> findByAccountId(FindByAccountIdForTodo params);
	
	Long countAll();

	void insert(TodoForInsert todo);

	boolean update(Todo todo);

	void delete(Todo todo);

	long countByFinished(boolean finished);
	
}