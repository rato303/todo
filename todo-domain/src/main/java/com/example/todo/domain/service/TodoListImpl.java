package com.example.todo.domain.service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.todo.domain.model.FindByAccountIdForTodo;
import com.example.todo.domain.model.Todo;

class TodoListImpl implements TodoList {
	
	Page<Todo> pagination;
	
	private TodoListImpl() {
	}

	@Override
	public int getTotalPages() {
		return pagination.getTotalPages();
	}

	@Override
	public long getTotalElements() {
		return pagination.getTotalElements();
	}

	@Override
	public <U> Page<U> map(Function<? super Todo, ? extends U> converter) {
		return pagination.map(converter);
	}

	@Override
	public int getNumber() {
		return pagination.getNumber();
	}

	@Override
	public int getSize() {
		return pagination.getSize();
	}

	@Override
	public int getNumberOfElements() {
		return pagination.getNumberOfElements();
	}

	@Override
	public List<Todo> getContent() {
		return pagination.getContent();
	}

	@Override
	public boolean hasContent() {
		return pagination.hasContent();
	}

	@Override
	public Sort getSort() {
		return pagination.getSort();
	}

	@Override
	public boolean isFirst() {
		return pagination.isFirst();
	}

	@Override
	public boolean isLast() {
		return pagination.isLast();
	}

	@Override
	public boolean hasNext() {
		return pagination.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return pagination.hasPrevious();
	}

	@Override
	public Pageable nextPageable() {
		return pagination.nextPageable();
	}

	@Override
	public Pageable previousPageable() {
		return pagination.previousPageable();
	}

	@Override
	public Iterator<Todo> iterator() {
		return pagination.iterator();
	}
	
	static TodoList  make(List<Todo> todos, long maxSize, FindByAccountIdForTodo params) {
		TodoListImpl instance = new TodoListImpl();
		Pageable pageable = PageRequest.of(params.getOffset(), params.getPageSize());
		instance.pagination = new PageImpl<Todo>(todos, pageable, maxSize);
		return instance;
	}

}
