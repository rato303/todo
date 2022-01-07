package com.example.todo.app;

import java.time.LocalDate;

import org.junit.Test;

public class AccountControllerTest {

	@Test
	public void test() {
		LocalDate plusDays = LocalDate.of(2022, 1, 6).plusDays(90);
		System.out.println(plusDays);
	}
	
	@Test
	public void test2() {
		LocalDate startDate = LocalDate.of(2022, 1, 6);
		for (int i = 0; i < 91; i++) {
			LocalDate c = startDate.plusDays(i);
			System.out.println(c);
		}
	}

}
