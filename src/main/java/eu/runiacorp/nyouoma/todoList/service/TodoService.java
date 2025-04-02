package eu.runiacorp.nyouoma.todoList.service;

import java.util.List;

import eu.runiacorp.nyouoma.todoList.model.Todo;
import eu.runiacorp.nyouoma.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        if (todo != null) {
            todo.setTitle(todoDetails.getTitle());
            todo.setDescription(todoDetails.getDescription());
            todo.setRecurring(todoDetails.isRecurring());
            todo.setDone(todoDetails.isDone());
            return todoRepository.save(todo);
        }
        return null;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}