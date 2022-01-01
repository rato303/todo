package com.example.todo.domain.repository.account;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:test-context.xml",
	"classpath:META-INF/spring/todo-infra.xml"
})
@Transactional
public class AccountRepositoryTest {
	
	@Inject
	AccountRepository accountRepository;

	@Test
	@Sql(scripts = "classpath:META-INF/AccountRepository/testFindAll.sql")
	public void testFindAll() {
		// Setup
		// Nothing
		
		// Exercise
		List<Account> actuals = accountRepository.findAll();
		
		// Verify
		assertThat(actuals).isNotNull();
		assertThat(actuals.size()).isEqualTo(3);
		
		Account actual1 = actuals.get(0);
		assertThat(actual1.getId()).isEqualTo("id1");
		assertThat(actual1.getName()).isEqualTo("ユーザー1");
		
		Account actual2 = actuals.get(1);
		assertThat(actual2.getId()).isEqualTo("id2");
		assertThat(actual2.getName()).isEqualTo("ユーザー2");
		
		Account actual3 = actuals.get(2);
		assertThat(actual3.getId()).isEqualTo("id3");
		assertThat(actual3.getName()).isEqualTo("ユーザー3");
	}

}
