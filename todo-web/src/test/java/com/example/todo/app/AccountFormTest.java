package com.example.todo.app;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.example.todo.domain.model.Authority;

public class AccountFormTest {
	
	AccountForm accountForm;
	
	@Before
	public void before() {
		accountForm = new AccountForm();
	}

	@Test
	public void testAsAuthorities() {
		// Setup
		Authority[] authorities = {Authority.ACCOUNT, Authority.TODO};
		accountForm.setAuthorities(Arrays.stream(authorities).map((a) -> a.getValue()).collect(Collectors.joining(",")));
		
		// Exercise
		List<Authority> actuals = accountForm.asAuthorities();
		
		// Verify
		assertThat(actuals).isNotNull();
		assertThat(actuals.size()).isEqualTo(2);
		
		Authority actual1 = actuals.get(0);
		assertThat(actual1).isEqualTo(Authority.ACCOUNT);
		
		Authority actual2 = actuals.get(1);
		assertThat(actual2).isEqualTo(Authority.TODO);
	}

}
