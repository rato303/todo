package com.example.todo.domain.service;

public interface AccountCreateService {

	void create(AccountForCreate account);
	
	public interface AccountForCreate {
		
		String getId();
		
		String getName();
		
		/**
		 * 暗号化前のパスワードを取得します。
		 * 
		 * @return 暗号化前のパスワード
		 */
		String getRawPassword();
		
	}
	
}
