package com.cesde.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger/OpenAPI configuration for documenting our API
 * 
 * This configuration creates the automatic documentation that can be viewed at:
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI userApiOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("🚀 CESDE - User Management API")
        .description("Simple REST API for learning Spring Boot 3.x - CESDE Student project")
        .version("1.0.0")
        .contact(new Contact()
          .name("CESDE Students")
          .email("estudiantes@cesde.edu.co")
          .url("https://cesde.edu.co")
        )
        .license(new License()
          .name("MIT License")
          .url("https://opensource.org/licenses/MIT")
        )
      )
      .servers(List.of(
        new Server()
          .url("http://localhost:8080")
          .description("🛠️ Local Development Server")
        )
      )
      .tags(List.of(
        new Tag()
          .name("👥 User Management")
          .description("CRUD operations for users: create, read, update, delete"),
        new Tag()
          .name("🔍 Searches")
          .description("Different ways to search for users"),
        new Tag()
          .name("📊 Statistics")
          .description("System counters and statistics")
      )
    );
  }
}
