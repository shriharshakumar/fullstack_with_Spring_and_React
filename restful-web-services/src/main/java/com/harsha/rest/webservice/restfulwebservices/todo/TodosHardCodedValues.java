package com.harsha.rest.webservice.restfulwebservices.todo;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodosHardCodedValues {

	static long idCounter = 1;

	static List<Todo> todos = new ArrayList<Todo>();

	static {
		todos.add(new Todo(++idCounter, "abcd", "asads", new Date(), true));
		todos.add(new Todo(++idCounter, "dddd", "sdddd", new Date(), true));
		todos.add(new Todo(++idCounter, "eeee", "rrrrr", new Date(), true));
	}
	
	public List<Todo> returnAll() {
		return todos;
	}
	
	public Todo deleteTodo(long id) {
		Todo todo = findById(id);
		
		Boolean isRemoved = todos.remove(todo);
		
		if(isRemoved) {
			return todo;
		}
		return null;
	}

	public Todo findById(long id) {
		// TODO Auto-generated method stub
		for(Todo todo:todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}else {
			deleteTodo(todo.id);
			todos.add(todo);
		}
		return todo;
	}

}
