package com.example.todo.app;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.domain.service.AccountCreateService;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Inject
	AccountCreateService accountCreateService;
	
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
		accountCreateService.create(accountForm.toAccountForCreate());
		return "redirect:/account/list";
	}

}
