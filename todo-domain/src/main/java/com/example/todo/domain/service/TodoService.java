package com.example.todo.domain.service;

import java.util.Collection;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	
    Collection<Todo> findAll();

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
    
}
