package com.example.todo.domain.repository.todo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.todo.domain.model.Todo;

public interface TodoRepository {
	
	Todo findOne(String todoId);

	List<Todo> findAll(Pageable pageable);
	
	Long countAll();

	void create(Todo todo);

	boolean update(Todo todo);

	void delete(Todo todo);

	long countByFinished(boolean finished);
	
}