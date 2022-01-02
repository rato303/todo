package com.example.todo.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.domain.model.TodoForCreate;
import com.example.todo.domain.model.TodoForInsert;
import com.example.todo.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoCreateServiceImpl implements TodoCreateService {
	
	private static final long MAX_UNFINISHED_COUNT = 50;

	TodoRepository todoRepository;
	
	@Inject
	TodoCreateServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public void create(TodoForCreate todo) {
		long unfinishedCount = todoRepository.countByFinished(false);
		if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage
					.fromText("[E001] The count of un-finished Todo must not be over " + MAX_UNFINISHED_COUNT + "."));
			throw new BusinessException(messages);
		}
		todoRepository.insert(new TodoForInsertImpl(todo));
	}
	
	private class TodoForInsertImpl implements TodoForInsert {
		
		TodoForCreate todo;
		
		TodoForInsertImpl(TodoForCreate todo) {
			this.todo = todo;
		}

		@Override
		public String getId() {
			return UUID.randomUUID().toString();
		}

		@Override
		public String getTitle() {
			return todo.getTitle();
		}

		@Override
		public Boolean isFinished() {
			return false;
		}

		@Override
		public LocalDateTime getCreatedAt() {
			return LocalDateTime.now();
		}

		@Override
		public String getAccountId() {
			return todo.getAccountId();
		}
		
	}
	
}
