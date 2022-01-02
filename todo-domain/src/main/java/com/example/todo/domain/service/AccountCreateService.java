package com.example.todo.domain.service;

import java.util.List;

import com.example.todo.domain.model.Authority;

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
		
		List<Authority> getAuthorities();
	}
	
}
