package eu.runiacorp.nyouoma.todoList.service;

import java.util.Calendar;
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
        // The createdDate will be automatically set by @CreationTimestamp
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        if (todo != null) {
            todo.setTitle(todoDetails.getTitle());
            todo.setDescription(todoDetails.getDescription());
            todo.setRecurring(todoDetails.isRecurring());
            todo.setDone(todoDetails.isDone());
            todo.setDaily(todoDetails.isDaily());
            // Optionnel : propager lastReset si nécessaire
            return todoRepository.save(todo);
        }
        return null;
    }

    public void resetDailyTodos() {
        List<Todo> todos = todoRepository.findAll();
        Calendar todayCal = Calendar.getInstance();
        int todayYear = todayCal.get(Calendar.YEAR);
        int todayDay = todayCal.get(Calendar.DAY_OF_YEAR);
        for (Todo todo : todos) {
            if (todo.isDaily()) {
                // Skip reset if the todo was created today
                if (todo.getCreatedDate() != null) {
                    Calendar creationCal = Calendar.getInstance();
                    creationCal.setTime(todo.getCreatedDate());
                    if (creationCal.get(Calendar.YEAR) == todayYear && creationCal.get(Calendar.DAY_OF_YEAR) == todayDay) {
                        continue;
                    }
                }
                boolean needReset = false;
                if (todo.getLastReset() == null) {
                    needReset = true;
                } else {
                    Calendar resetCal = Calendar.getInstance();
                    resetCal.setTime(todo.getLastReset());
                    if (resetCal.get(Calendar.YEAR) != todayYear || resetCal.get(Calendar.DAY_OF_YEAR) != todayDay) {
                        needReset = true;
                    }
                }
                if (needReset) {
                    todo.setDone(false);
                    todo.setLastReset(todayCal.getTime());
                    todoRepository.save(todo);
                }
            }
        }
    }

    public void strictResetDailyTodos() {
        List<Todo> todos = todoRepository.findAll();
        for (Todo todo : todos) {
            if (todo.isDaily()) {
                todo.setDone(false);
                todoRepository.save(todo);
            }
        }
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}