package eu.runiacorp.nyouoma.todoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@SpringBootApplication
@EnableScheduling
public class TodoListApplication {
    public static void main(String[] args) {
        // Ensure the data directory exists
        File dataDir = new File("E:/projet/TodoList/data");
        if (!dataDir.exists()) {
            if (dataDir.mkdirs()) {
                System.out.println("Data directory created: " + dataDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create data directory: " + dataDir.getAbsolutePath());
            }
        }

        SpringApplication.run(TodoListApplication.class, args);
    }
}