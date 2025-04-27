package com.example.todo_list_crud.service;

import java.util.List;
import java.util.Optional;

import com.example.todo_list_crud.model.Todo;

public interface TodoService {
    List<Todo> getAllTodos();
    List<Todo> getCompletedTodos();
    List<Todo> getIncompleteTodos();
    Optional<Todo> getTodoById(Long id);
    Todo saveTodo(Todo todo);
    void deleteTodo(Long id);
    Todo updateTodo(Todo todo);
    void toggleTodoStatus(Long id);
}
