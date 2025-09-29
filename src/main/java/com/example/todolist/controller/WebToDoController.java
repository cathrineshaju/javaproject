package com.example.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todolist.model.ToDoItem;
import com.example.todolist.service.ToDoItemService;


@Controller
public class WebToDoController {


private final ToDoItemService service;


public WebToDoController(ToDoItemService service) {
this.service = service;
}


@GetMapping("/")
public String home(Model model) {
    model.addAttribute("todos", service.findAll());
    return "list";
}


@GetMapping("/todos/new")
public String showCreateForm(Model model) {
model.addAttribute("todo", new ToDoItem());
return "form";
}


@PostMapping("/todos")
public String createToDo(@ModelAttribute ToDoItem todo, BindingResult result) {
if (result.hasErrors()) {
return "form";
}
service.save(todo);
return "redirect:/";
}


@GetMapping("/todos/{id}/edit")
public String showEditForm(@PathVariable Long id, Model model) {
var t = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ToDo Id:" + id));
model.addAttribute("todo", t);
return "form";
}


@PostMapping("/todos/{id}/update")
public String updateToDoItem(@PathVariable Long id, @ModelAttribute ToDoItem todo, BindingResult result) {
if (result.hasErrors()) {
return "form";
}
todo.setId(id);
service.save(todo);
return "redirect:/";
}


@GetMapping("/todos/{id}/delete")
public String deleteToDo(@PathVariable Long id) {
service.deleteById(id);
return "redirect:/";
}


@GetMapping("/todos/{id}/toggle")
public String toggleComplete(@PathVariable Long id) {
var t = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ToDo Id:" + id));
t.setCompleted(!t.isCompleted());
service.save(t);
return "redirect:/";
}
}