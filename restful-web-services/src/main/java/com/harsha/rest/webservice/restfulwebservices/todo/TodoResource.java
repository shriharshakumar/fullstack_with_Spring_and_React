package com.harsha.rest.webservice.restfulwebservices.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoResource {
	
	@Autowired
	public TodosHardCodedValues todos;
	
	@GetMapping(path="/users/{userName}/todos")
	public List<Todo> getAllTodos(@PathVariable String userName){
		return todos.returnAll();
	}
	
	@GetMapping(path="/users/{userName}/todos/{id}")
	public Todo getTodo(@PathVariable String userName, @PathVariable long id) {
		return todos.findById(id);
	}
	
	@DeleteMapping(path="/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id){
		Todo todo = todos.deleteTodo(id);
		
		if(todo == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping(path="/users/{userName}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id, @RequestBody Todo todo) {
		
		todo = todos.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		
	}
	
	@PostMapping(path="/users/{userName}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String userName, @RequestBody Todo todo) {
		
		Todo createdTodo = todos.save(todo);
		
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
