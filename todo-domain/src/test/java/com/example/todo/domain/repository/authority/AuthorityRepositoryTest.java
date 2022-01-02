package com.example.todo.domain.repository.authority;

import static org.assertj.core.api.Assertions.*;

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

import com.example.todo.domain.model.AuthorityForInsert;
import com.example.todo.domain.model.AuthorityImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/test-infra.xml"
})
@Transactional
public class AuthorityRepositoryTest {
	
	@Inject
	Changes changes;
	
	@Inject
	AuthorityRepository authorityRepository;

	@Test
	@Sql(scripts = "classpath:META-INF/AuthorityRepository/testFindByAccountId.sql")
	public void testFindByAccountId() {
		// Setup
		String accountId = "user1";
		
		// Exercise
		List<AuthorityImpl> actuals = authorityRepository.findByAccountId(accountId);
		
		assertThat(actuals).isNotNull();
		assertThat(actuals.size()).isEqualTo(2);
		
		AuthorityImpl actual1 = actuals.get(0);
		assertThat(actual1.getName()).isEqualTo("account");
		AuthorityImpl actual2 = actuals.get(1);
		assertThat(actual2.getName()).isEqualTo("todo");
	}

	@Test
	@Sql(scripts = "classpath:META-INF/AuthorityRepository/testInsert.sql")
	public void testInsert() {
		// Setup
		AuthorityForInsertImplForJUnit authority = new AuthorityForInsertImplForJUnit();
		authority.accountId = "user1";
		authority.name = "todo";
		
		// Before Exercise
		changes.setStartPointNow();
		
		// Exercise
		authorityRepository.insert(authority);
		
		// After Exercise
		changes.setEndPointNow();
		
		// Verify
		Assertions
			.assertThat(changes)
			.hasNumberOfChanges(1)
				.ofCreationOnTable("authority")
					.hasNumberOfChanges(1)
			.changeOnTable("authority")
				.isCreation()
					.rowAtEndPoint()
						.value("account_id").isEqualTo(authority.accountId)
						.value("name").isEqualTo(authority.name);
	}
	
	private class AuthorityForInsertImplForJUnit implements AuthorityForInsert {
		
		String accountId;
		String name;

		@Override
		public String getAccountId() {
			return accountId;
		}

		@Override
		public String getName() {
			return name;
		}
		
	}
	
}
