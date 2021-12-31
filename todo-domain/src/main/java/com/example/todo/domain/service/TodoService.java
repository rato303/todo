package com.example.todo.domain.service;

import org.springframework.data.domain.Pageable;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	
	TodoList findAll(Pageable pageable);

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
    
}
