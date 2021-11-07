package com.example.todo.app;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.app.TodoForm.TodoCreate;
import com.example.todo.app.TodoForm.TodoDelete;
import com.example.todo.app.TodoForm.TodoFinish;
import com.example.todo.domain.model.AccountUserDetails;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.TodoService;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("todo")
public class TodoController {
    @Inject
    TodoService todoService;

    @Inject
    Mapper beanMapper;
    
    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @RequestMapping(value = "list")
    public String list(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        model.addAttribute("loginAccount", userDetails);
        return "todo/list";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(
            @Validated({ Default.class, TodoCreate.class }) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes, @AuthenticationPrincipal AccountUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return list(model, userDetails);
        }

        Todo todo = beanMapper.map(todoForm, Todo.class);

        try {
            todoService.create(todo);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, userDetails);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Created successfully!")));
        return "redirect:/todo/list";
    }

    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public String finish(
            @Validated({ Default.class, TodoFinish.class }) TodoForm form,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes, @AuthenticationPrincipal AccountUserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            return list(model, userDetails);
        }

        try {
            todoService.finish(form.getTodoId());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, userDetails);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Finished successfully!")));
        return "redirect:/todo/list";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST) // (1)
    public String delete(
            @Validated({ Default.class, TodoDelete.class }) TodoForm form,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes, @AuthenticationPrincipal AccountUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return list(model, userDetails);
        }

        try {
            todoService.delete(form.getTodoId());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, userDetails);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Deleted successfully!")));
        return "redirect:/todo/list";
    }

}