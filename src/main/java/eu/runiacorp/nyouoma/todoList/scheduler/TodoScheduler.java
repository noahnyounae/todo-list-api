package eu.runiacorp.nyouoma.todoList.scheduler;

import eu.runiacorp.nyouoma.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TodoScheduler {

    @Autowired
    private TodoService todoService;

    // Exécuté toutes les minutes (60000 millisecondes)
    @Scheduled(fixedRate = 60000)
    public void processRecurringTodos() {
        // Traitement des todos récurrents et daily
        todoService.resetDailyTodos();
    }
}
