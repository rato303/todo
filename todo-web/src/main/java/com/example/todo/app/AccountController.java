package com.example.todo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@ModelAttribute
	public AccountForm setUpForm() {
		return new AccountForm();
	}
	
	@GetMapping(value = "list")
	public String list() {
		return "account/list";
	}
	
	@PostMapping(value = "create")
	public String create(AccountForm accountForm) {
		System.out.println(accountForm.toString());
		return "redirect:/account/list";
	}

}
