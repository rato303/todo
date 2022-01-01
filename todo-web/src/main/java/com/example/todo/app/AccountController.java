package com.example.todo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@GetMapping(value = "list")
	public String list() {
		return "account/list";
	}

}
