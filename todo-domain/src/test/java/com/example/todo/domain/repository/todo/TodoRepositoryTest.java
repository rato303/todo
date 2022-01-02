package com.example.todo.domain.repository.todo;

import static org.assertj.core.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.assertj.db.api.Assertions;
import org.assertj.db.type.Changes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.FindByAccountIdForTodo;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoForInsert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/test-infra.xml"
})
@Transactional
public class TodoRepositoryTest {

	@Inject
	TodoRepository todoRepository;
	
	@Inject
	Changes changes;
	
	@Test
	@Sql(scripts = "classpath:META-INF/TodoRepository/testFindByAccountId.sql")
	public void testFindByAccountId() {
		// Setup
		FindByAccountIdForTodoImplForJUnit params = new FindByAccountIdForTodoImplForJUnit();
		params.offset = 0;
		params.pageSize = 5;
		params.accountId = "user1";

		// Exercise
		List<Todo> actuals = todoRepository.findByAccountId(params);

		// Verify
		assertThat(actuals).isNotNull();
		assertThat(actuals.size()).isEqualTo(params.pageSize);

		Todo actual1 = actuals.get(0);
		assertThat(actual1.getTodoTitle()).isEqualTo("todo1");
		
		Todo actual2 = actuals.get(1);
		assertThat(actual2.getTodoTitle()).isEqualTo("todo3");
		
		Todo actual3 = actuals.get(2);
		assertThat(actual3.getTodoTitle()).isEqualTo("todo5");
		
		Todo actual4 = actuals.get(3);
		assertThat(actual4.getTodoTitle()).isEqualTo("todo7");
		
		Todo actual5 = actuals.get(4);
		assertThat(actual5.getTodoTitle()).isEqualTo("todo8");
	}

	@Test
	@Sql(scripts = "classpath:META-INF/TodoRepository/testFindAll.sql")
	public void testCountAll() {
		// Setup
		// Nothing

		// Exercise
		Long actual = todoRepository.countAll();

		// Verify
		assertThat(actual).isEqualTo(9);
	}

	@Test
	@Sql(scripts = "classpath:META-INF/TodoRepository/testInsert.sql")
	public void testInsert() {
		// Setup
		TodoForInsertImplForJUnit todo = new TodoForInsertImplForJUnit();
		todo.id = "id1";
		todo.title = "やること1";
		todo.finished = false;
		todo.createdAt = LocalDateTime.of(2022, 1, 2, 14, 46);
		todo.accountId = "account1";

		// Before Exercise
		changes.setStartPointNow();

		// Exercise
		todoRepository.insert(todo);

		// After Exercise
		changes.setEndPointNow();

		// Verify
		Assertions
			.assertThat(changes)
			.hasNumberOfChanges(1)
			.ofCreationOnTable("todo")
				.hasNumberOfChanges(1)
			.changeOnTable("todo")
				.isCreation()
				.rowAtEndPoint()
				.value("todo_id").isEqualTo("id1")
				.value("todo_title").isEqualTo("やること1")
				.value("finished").isFalse()
				.value("created_at").isEqualTo(Timestamp.valueOf(LocalDateTime.of(2022, 1, 2, 14, 46)))
				.value("account_id").isEqualTo("account1");
	}
	
	private class FindByAccountIdForTodoImplForJUnit implements FindByAccountIdForTodo {
		
		String accountId;
		int pageSize;
		int offset;

		@Override
		public String getAccountId() {
			return accountId;
		}

		@Override
		public int getPageSize() {
			return pageSize;
		}

		@Override
		public int getOffset() {
			return offset;
		}
		
	}

	private class TodoForInsertImplForJUnit implements TodoForInsert {

		String id;
		String title;
		boolean finished;
		LocalDateTime createdAt;
		String accountId;

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getTitle() {
			return title;
		}

		@Override
		public Boolean isFinished() {
			return finished;
		}

		@Override
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		@Override
		public String getAccountId() {
			return accountId;
		}

	}

}
