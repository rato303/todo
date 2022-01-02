package com.example.todo.app;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.todo.domain.model.AccountUserDetails;
import com.example.todo.domain.model.TodoForCreate;

public class TodoForm implements Serializable {
	public static interface TodoCreate {
	};

	public static interface TodoFinish {
	};

	// (1)
	public static interface TodoDelete {
	}

	private static final long serialVersionUID = 1L;

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
	
	public TodoForCreate toTodoForCreate(AccountUserDetails accountUserDetails) {
		return new TodoForCreateImpl(this, accountUserDetails);
	}
	
	private class TodoForCreateImpl implements TodoForCreate {
		
		TodoForm form;
		AccountUserDetails accountUserDetails;
		
		TodoForCreateImpl(TodoForm form, AccountUserDetails accountUserDetails) {
			this.form = form;
			this.accountUserDetails = accountUserDetails;
		}

		@Override
		public String getTitle() {
			return form.getTodoTitle();
		}

		@Override
		public String getAccountId() {
			return this.accountUserDetails.getId();
		}
		
	}

}