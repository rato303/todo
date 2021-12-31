package com.example.todo.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	
    List<Todo> findAll(Pageable pageable);

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
    
}
