package eu.runiacorp.nyouoma.todoList.repository;

import eu.runiacorp.nyouoma.todoList.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées si nécessaire
}

