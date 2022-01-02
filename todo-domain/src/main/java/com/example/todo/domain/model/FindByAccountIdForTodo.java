package com.example.todo.domain.model;

import org.springframework.data.domain.Pageable;

/**
 * todoをアカウント識別子で取得するパラメーター
 * 
 * @author t-kimura
 *
 */
public interface FindByAccountIdForTodo {
	
	String getAccountId();

	/**
	 * @see Pageable#getPageSize()
	 */
	int getPageSize();
	
	/**
	 * @see Pageable#getOffset()
	 */
	int getOffset();
	
}
