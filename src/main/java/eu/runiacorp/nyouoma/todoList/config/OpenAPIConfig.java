package eu.runiacorp.nyouoma.todoList.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Todo List API",
                version = "v1",
                description = "API pour la gestion d'une todo list avec événements uniques et récurrents"
        )
)
public class OpenAPIConfig {
    // Configuration personnalisée si nécessaire
}
