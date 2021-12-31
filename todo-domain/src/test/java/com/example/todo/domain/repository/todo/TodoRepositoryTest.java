package com.example.todo.domain.repository.todo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.Todo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:test-context.xml",
	"classpath:META-INF/spring/todo-infra.xml"
})
@Transactional
public class TodoRepositoryTest {
	
	@Inject
	TodoRepository todoRepository;

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

}
