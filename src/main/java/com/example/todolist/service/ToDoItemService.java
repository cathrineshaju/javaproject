package com.example.todolist.service;

import com.example.todolist.model.ToDoItem;




import com.example.todolist.repository.ToDoItemRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ToDoItemService {


private ToDoItemRepository repo = null;


public ToDoItemService(ToDoItemRepository repo) {
this.repo = repo;
}


public List<ToDoItem> findAll() { return repo.findAll(); }
public Optional<ToDoItem> findById(Long id) { return repo.findById(id); }
public ToDoItem save(ToDoItem t) { return repo.save(t); }
public void deleteById(Long id) { repo.deleteById(id); }
}