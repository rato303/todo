package com.example.todo.domain.model;

public interface AccountForInsert {

	String getId();
	
	String getName();
	
	/**
	 * 暗号化後のパスワードを取得します。
	 * 
	 * @return 暗号化後のパスワード
	 */
	String getPassword();

}
