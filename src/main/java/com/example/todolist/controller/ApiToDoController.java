package com.example.todolist.controller;






	import com.example.todolist.model.ToDoItem;
	import com.example.todolist.service.ToDoItemService;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;


	import java.util.List;


	@RestController
	@RequestMapping("/api/todos")
	public class ApiToDoController {


	private final ToDoItemService service;


	public ApiToDoController(ToDoItemService service) {
	this.service = service;
	}


	@GetMapping
	public List<ToDoItem> all() { return service.findAll(); }


	@GetMapping("/{id}")
	public ResponseEntity<ToDoItem> get(@PathVariable Long id) {
	return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PostMapping
	public ToDoItem create(@RequestBody ToDoItem todo) { return service.save(todo); }


	@PutMapping("/{id}")
	public ResponseEntity<ToDoItem> update(@PathVariable Long id, @RequestBody ToDoItem todo) {
	return service.findById(id).map(existing -> {
	existing.setTitle(todo.getTitle());
	existing.setDescription(todo.getDescription());
	existing.setCompleted(todo.isCompleted());
	service.save(existing);
	return ResponseEntity.ok(existing);
	}).orElseGet(() -> ResponseEntity.notFound().build());
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	service.deleteById(id);
	return ResponseEntity.noContent().build();
	}
	}