package com.kwikunka.List_todo.controller;

import com.kwikunka.List_todo.model.Todo;
import com.kwikunka.List_todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kwik on 06.02.2017.
 */
@Controller
public class TodoController {
    private TodoService todoService;
    private static byte filterComplete = 2;

    public static byte getFilterComplete() {
        return filterComplete;
    }

    public static void setFilterComplete(byte filterComplete) {
        TodoController.filterComplete = filterComplete;
    }

    @Autowired(required = true)
    @Qualifier(value = "todoService")

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "to_do", method = RequestMethod.GET)
    public String listTodo(Model model) {

        model.addAttribute("todo", new Todo());
        if  (getFilterComplete() == 1) {
            model.addAttribute("listTodo", this.todoService.listTodoFilter(true));
            model.addAttribute("filter", "Completed");
        }
        else if (getFilterComplete() == 0) {
            model.addAttribute("listTodo", this.todoService.listTodoFilter(false));
            model.addAttribute("filter", "Not completed");
        }
        else {
            model.addAttribute("filter", "All   ");
            model.addAttribute("listTodo", this.todoService.listTodo());
        }
        return "to_do";
    }


    @RequestMapping(value = "/filter/{id}")
    public String listTodoFilter(Model model, @PathVariable("id") byte id) {
        setFilterComplete(id);
        return "redirect:/to_do";
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


















