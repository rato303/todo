package com.example.todo.app;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.todo.domain.service.LoginAccount;

public class TodoForm implements Serializable {
	
	public static interface TodoCreate {
	};

	public static interface TodoFinish {
	};

	// (1)
	public static interface TodoDelete {
	}

	private static final long serialVersionUID = 1L;
	
	private LoginAccount loginAccount;

	// (2)
	@NotNull(groups = { TodoFinish.class, TodoDelete.class })
	private String todoId;

	@NotNull(groups = { TodoCreate.class })
	@Size(min = 1, max = 30, groups = { TodoCreate.class })
	private String todoTitle;

	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}
	
	public String getFullName() {
		return this.loginAccount.getFullName();
	}

	public void setLoginAccount(LoginAccount loginAccount) {
		this.loginAccount = loginAccount;
	}
	
}