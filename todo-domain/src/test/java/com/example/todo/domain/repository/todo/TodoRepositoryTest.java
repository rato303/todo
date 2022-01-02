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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
	@Sql(scripts = "classpath:META-INF/TodoRepository/testFindAll.sql")
	public void testFindAll() {
		// Setup
		int pageNumber = 0;
		int pageSize = 5;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		// Exercise
		List<Todo> actuals = todoRepository.findAll(pageable);

		// Verify
		assertThat(actuals).isNotNull();
		assertThat(actuals.size()).isEqualTo(pageSize);

		Todo actual1 = actuals.get(0);
		assertThat(actual1.getTodoTitle()).isEqualTo("todo1");
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
