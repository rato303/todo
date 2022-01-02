package com.example.todo.domain.service;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	
    Todo finish(String todoId);

    void delete(String todoId);
    
}
