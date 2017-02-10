package com.kwikunka.List_todo.controller;

import com.kwikunka.List_todo.model.Todo;
import com.kwikunka.List_todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kwik on 06.02.2017.
 */
@Controller
public class TodoController {
    private TodoService todoService;
    int offset = 0;
    int num = 5;
    public static int count = 0;

    @Autowired(required = true)
    @Qualifier(value = "todoService")

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "to_do", method = RequestMethod.GET)
    public String listTodo(@RequestParam(value="numpage", required = false) Integer numpage, @RequestParam(value="filt", required = false) Byte filt, Model model) {
        if (numpage == null) numpage = 1;
        if (filt == null) filt = 2;
        offset = numpage * num - num;
        List<Todo> todoListFull = null;
        List<Todo> todoList = this.todoService.getAllpages(offset, num, filt);
        if (filt == 1) {
            todoListFull = this.todoService.listTodo(true);
        }
        else if (filt == 0) {
            todoListFull = this.todoService.listTodo(false);
        }
        else {
            todoListFull = this.todoService.listTodo();
        }


        if (todoListFull.size() < num) {
            count = 1;
        }
        else if (todoListFull.size() > num && todoListFull.size() % num > 0 ) {
            count = todoListFull.size() / num + 1;
        }
        else {
            count = todoListFull.size() / num;
        }

        model.addAttribute("count", count);
        model.addAttribute("todoList", todoList);
        model.addAttribute("todoListFull", todoListFull);
        model.addAttribute("numpage", numpage);
        model.addAttribute("filt", filt);
        model.addAttribute("todo", new Todo());

        return "to_do";
    }


    @RequestMapping(value = "/to_do/add", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute("todo") Todo todo) {
        this.todoService.addTodo(todo);
        return "redirect:/to_do";
    }

    @RequestMapping(value = "/to_do/edit", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute("todo") Todo todo) {
        if (todo.getIdTODO() != 0) {
            this.todoService.updateTodo(todo);
        }
        return "redirect:/to_do";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id){
        this.todoService.deleteTodo(id);
        return "redirect:/to_do";
    }

    @RequestMapping("view/{id}")
    public String todoData(@PathVariable("id") int id, Model model){
        model.addAttribute("todo", this.todoService.getTodoById(id));
        return "view";
    }
}


















