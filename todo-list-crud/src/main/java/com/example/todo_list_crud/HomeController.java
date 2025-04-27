package com.example.todo_list_crud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/hello")
    public String home() {
        return "Welcome to the Todo List CRUD Application!";
    }
    
}
