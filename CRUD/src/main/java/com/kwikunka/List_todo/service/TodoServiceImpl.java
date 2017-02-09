package com.kwikunka.List_todo.service;

import com.kwikunka.List_todo.DAO.TodoDAO;
import com.kwikunka.List_todo.DAO.TodoDAOimpl;
import com.kwikunka.List_todo.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kwik on 05.02.2017.
 */
@Service
public class TodoServiceImpl implements TodoService {
    private TodoDAO todoDao;

    public void setTodoDao(TodoDAO todoDao) {
        this.todoDao = todoDao;
    }

    @Transactional
    public void addTodo(Todo todo) {
        this.todoDao.addTODO(todo);
    }

    @Transactional
    public void updateTodo(Todo todo) {
        this.todoDao.updateTODO(todo);
    }

    @Transactional
    public void deleteTodo(int id) {
        this.todoDao.deleteTODO(id);

    }

    @Transactional
    public Todo getTodoById(int id) {
        return this.todoDao.getTODObyId(id);
    }

    @Transactional
    public List<Todo> listTodo() {
        return this.todoDao.listTODO();
    }

    @Transactional
    public List<Todo> listTodoFilter(boolean isCompl) {
        return this.todoDao.listTODOFilter(isCompl);
    }
}
