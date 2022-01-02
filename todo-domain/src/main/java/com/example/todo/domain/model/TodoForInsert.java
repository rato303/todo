package com.example.todo.domain.model;

import java.time.LocalDateTime;

/**
 * todoテーブル挿入時パラメーター
 * 
 * @author t-kimura
 */
public interface TodoForInsert {

	String getId();
	
	String getTitle();
	
	Boolean isFinished();
	
	LocalDateTime getCreatedAt();
	
	String getAccountId();
	
}
