package com.kwikunka.List_todo.service;

import com.kwikunka.List_todo.model.Todo;

import java.util.List;

/**
 * Created by kwik on 05.02.2017.
 */
public interface TodoService {
    public void addTodo(Todo todo);
    public void updateTodo(Todo todo);
    public void deleteTodo(int id);
    public Todo getTodoById(int id);
    public List<Todo> listTodo();
    public List<Todo> listTodo(boolean isComplete);
    public List<Todo> getAllpages(int begin, int num, byte filt);


}
