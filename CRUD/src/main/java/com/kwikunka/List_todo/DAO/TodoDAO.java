package com.kwikunka.List_todo.DAO;

import com.kwikunka.List_todo.model.Todo;

import java.util.List;

/**
 * Created by kwik on 05.02.2017.
 */
public interface TodoDAO {
    public void addTODO(Todo todo);
    public void updateTODO(Todo todo);
    public void deleteTODO(int id);
    public Todo getTODObyId(int id);
    public List<Todo> listTODO();
    public List<Todo> listTODOFilter(boolean isComplete);

}
