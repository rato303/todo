package com.example.todo.domain.model;

import java.util.Arrays;

public enum Authority {

	TODO("todo"),
	ACCOUNT("account");

	String value;

	Authority(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static Authority stringOf(String value) {
		return Arrays.stream(values()).filter((v) -> v.value.equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
