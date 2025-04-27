package com.example.todo_list_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo_list_crud.model.Todo;
import com.example.todo_list_crud.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("incompleteTodos", todoService.getIncompleteTodos().size());
        model.addAttribute("completedTodos", todoService.getCompletedTodos().size());
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("viewingCompleted", false); // add this
        model.addAttribute("viewingIncomplete", false); // add this
        return "index";
    }

    @GetMapping("/todos")
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("incompleteTodos", todoService.getIncompleteTodos().size());
        model.addAttribute("completedTodos", todoService.getCompletedTodos().size());
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("viewingCompleted", false); // add this
        model.addAttribute("viewingIncomplete", false); // add this
        return "index";
    }

    @PostMapping("/todos")
    public String createTodo(@Valid @ModelAttribute("newTodo") Todo todo, 
                            BindingResult result, 
                            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("error", "Please provide a valid title for the todo");
            return "redirect:/";
        }
        
        todoService.saveTodo(todo);
        attributes.addFlashAttribute("success", "Todo added successfully!");
        return "redirect:/";
    }

    @GetMapping("/todos/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        
        model.addAttribute("todo", todo);
        return "todo-form";
    }

    @PostMapping("/todos/{id}")
    public String updateTodo(@PathVariable Long id, 
                           @Valid @ModelAttribute("todo") Todo todo,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("error", "Please provide valid data");
            return "todo-form";
        }
        
        todoService.updateTodo(todo);
        attributes.addFlashAttribute("success", "Todo updated successfully!");
        return "redirect:/";
    }

    @GetMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes attributes) {
        todoService.deleteTodo(id);
        attributes.addFlashAttribute("success", "Todo deleted successfully!");
        return "redirect:/";
    }

    @GetMapping("/todos/{id}/toggle")
    public String toggleTodoStatus(@PathVariable Long id) {
        todoService.toggleTodoStatus(id);
        return "redirect:/";
    }

    @GetMapping("/todos/completed")
    public String viewCompletedTodos(Model model) {
        model.addAttribute("todos", todoService.getCompletedTodos());
        model.addAttribute("incompleteTodos", todoService.getIncompleteTodos().size());
        model.addAttribute("completedTodos", todoService.getCompletedTodos().size());
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("viewingCompleted", true);
        return "index";
    }

    @GetMapping("/todos/incomplete")
    public String viewIncompleteTodos(Model model) {
        model.addAttribute("todos", todoService.getIncompleteTodos());
        model.addAttribute("incompleteTodos", todoService.getIncompleteTodos().size());
        model.addAttribute("completedTodos", todoService.getCompletedTodos().size());
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("viewingIncomplete", true);
        return "index";
    }
}