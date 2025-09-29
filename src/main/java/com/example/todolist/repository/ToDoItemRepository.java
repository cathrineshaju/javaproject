package com.example.todolist.repository;


import com.example.todolist.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}