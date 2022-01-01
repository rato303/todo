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
import com.example.todo.domain.model.AccountForInsert;

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
	
	@Test
	@Sql(scripts = "classpath:META-INF/AccountRepository/testCreate.sql")
	public void testCreate() {
		
		// Setup
		AccountForInsertImplForJUnit account = new AccountForInsertImplForJUnit();
		account.id = "id1";
		account.name = "ユーザー1";
		account.password = "pas1";
		
		// Exercise
		accountRepository.insert(account);
		
		// Verify
		assertThat(accountRepository.findAll().size()).isEqualTo(1);
		
		// TODO @Injectで取得できるDataSouceはRepositoryで利用しているConnectionと別物になっているので下記のコードで検証できない
//		@Inject
//		DataSource dataSource;
//		// Before Exercise
//		changes.setStartPointNow();
//		
//		// Exercise
//		accountRepository.insert(account);
//		
//		// After Exercise
//		changes.setEndPointNow();
//		
//		// Verify
//		Assertions
//			.assertThat(changes)
//			.hasNumberOfChanges(1)
//			.ofCreationOnTable("account")
//				.hasNumberOfChanges(1)
//			.changeOnTable("account")
//				.isCreation()
//				.rowAtEndPoint()
//					.value("account_id").isEqualTo("id1")
//					.value("account_name").isEqualTo("ユーザー1")
//					.value("password").isEqualTo("pas1");
	}
	
	private class AccountForInsertImplForJUnit implements AccountForInsert {
		
		String id;
		String name;
		String password;

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getPassword() {
			return password;
		}
		
	}

}
