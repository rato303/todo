package com.example.todo.domain.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.todo.TodoRepository;

@Service // (1)
@Transactional // (2)
public class TodoServiceImpl implements TodoService {

	@Inject // (3)
	TodoRepository todoRepository;

	// (4)
	public Todo findOne(String todoId) {
		Todo todo = todoRepository.findOne(todoId);
		if (todo == null) {
			// (5)
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E404] The requested Todo is not found. (id=" + todoId + ")"));
			// (6)
			throw new ResourceNotFoundException(messages);
		}
		return todo;
	}

	@Override
	@Transactional(readOnly = true) // (7)
	public TodoList findAll(Pageable pageable) {
		List<Todo> todos = todoRepository.findAll(pageable);
		Long todoCount = todoRepository.countAll();
		return null;
	}

	@Override
	public Todo finish(String todoId) {
		Todo todo = findOne(todoId);
		if (todo.isFinished()) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E002] The requested Todo is already finished. (id=" + todoId + ")"));
			throw new BusinessException(messages);
		}
		todo.setFinished(true);
		todoRepository.update(todo);
		/*
		 * REMOVE THIS LINE IF YOU USE JPA todoRepository.save(todo); // (11) REMOVE
		 * THIS LINE IF YOU USE JPA
		 */
		return todo;
	}

	@Override
	public void delete(String todoId) {
		Todo todo = findOne(todoId);
		todoRepository.delete(todo);
	}
}