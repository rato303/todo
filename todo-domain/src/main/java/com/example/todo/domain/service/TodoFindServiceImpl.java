package com.example.todo.domain.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.FindByAccountIdForTodo;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoFindServiceImpl implements TodoFindService {
	
	@Inject
	TodoRepository todoRepository;
	
	TodoFindServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public TodoList find(FindByAccountIdForTodo params) {
		List<Todo> todos = todoRepository.findByAccountId(params);
		Long todoCount = todoRepository.countAll();
		return TodoListImpl.make(todos, todoCount, params);
	}

}
