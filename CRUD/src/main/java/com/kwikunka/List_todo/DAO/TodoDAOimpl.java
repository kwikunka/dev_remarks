package com.kwikunka.List_todo.DAO;

import com.kwikunka.List_todo.model.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwik on 05.02.2017.
 */
@Repository
public class TodoDAOimpl implements TodoDAO {
    private static final Logger logger = LoggerFactory.getLogger(TodoDAOimpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTODO(Todo todo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(todo);
        logger.info("Todo successfully added. Todo details: " + todo);
    }

    public void updateTODO(Todo todo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(todo);
        logger.info("Todo successfully updated. Todo detailes: " + todo);
    }

    public void deleteTODO(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo todo = (Todo) session.load(Todo.class, new Integer(id));
        if (todo != null) {
            session.delete(todo);
        }
        logger.info("Todo successfully deleted. Todo detailes: " + todo);
    }

    public Todo getTODObyId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo todo = (Todo) session.load(Todo.class, new Integer(id));
        logger.info("Todo successfully loaded. Todo detailes: " + todo);
        return todo;
    }

    @SuppressWarnings("unchecked")
    public List<Todo> listTODO() {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from Todo";
        Query query = session.createQuery(hql);
        List<Todo> todoList = query.list();
        for (Todo todo : todoList) {
            logger.info("Todo list: " + todo);
        }

        return todoList;
    }

    @SuppressWarnings("unchecked")
    public List<Todo> listTODO(boolean isComplete) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from Todo where isCompleted = :complete";
        Query query = session.createQuery(hql);
        query.setParameter("complete", isComplete);
        List<Todo> todoList = query.list();
        for(Todo todo: todoList) {
            logger.info("Todo list: " + todo);
        }

        return todoList;
    }

    @SuppressWarnings("unchecked")
    public List<Todo> getAllpages(int begin, int num, byte filt) {
        List<Todo> todoList = null;
        if (filt == 1) {
            todoList = listTODO(true);
        }
        else if (filt == 0) {
            todoList = listTODO(false);
        }
        else {
            todoList = listTODO();
        }

        List<Todo> todoList_end = new ArrayList<Todo>();
        if (todoList.size() <= begin) {
            for(Todo todo: todoList) {
                logger.info("Todo list: " + todo);
            }
            return null;
        }
        else if (todoList.size() < (begin + num) && begin < todoList.size()) {
            for (int i = begin; i < todoList.size(); i++){
                todoList_end.add(todoList.get(i));
                logger.info("Todo list page: " + todoList.get(i));
            }
            return todoList_end;
        }
        else {
            for (int i = begin; i < begin + num; i++){
                todoList_end.add(todoList.get(i));
                logger.info("Todo list page: " + todoList.get(i));
            }
            return todoList_end;
        }
    }
}