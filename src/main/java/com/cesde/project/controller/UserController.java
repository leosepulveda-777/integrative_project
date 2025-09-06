package com.cesde.project.controller;

import com.cesde.project.dto.UserDTO;
import com.cesde.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users") // -> localhost:8080/api/users
@Tag(name = "👥 User Management", description = "CRUD operations for users: create, read, update, delete")
public class UserController {
  // Inyectar el servicio
  @Autowired
  private UserService userService;

  @GetMapping
  @Operation(summary = "Get all users", description = "Retrieve a list of all users")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<Page<UserDTO>> getAllUsers(
      @Parameter(description = "Page number (0..N)", example = "0") @RequestParam(defaultValue = "0") int page,
      @Parameter(description = "Number of records per page", example = "10") @RequestParam(defaultValue = "10") int size,
      @Parameter(description = "Sort by field", example = "firstName") @RequestParam(defaultValue = "firstName") String sortBy,
      @Parameter(description = "Sort direction", example = "asc") @RequestParam(defaultValue = "asc") String sortDir
    ) {
    try {
      Sort.Direction direction = Sort.Direction.fromString(sortDir);
      Sort sort = Sort.by(direction, sortBy);

      Pageable pageable = PageRequest.of(page, size, sort);

      Page<UserDTO> users = userService.getAllUsers(pageable);
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
    @ApiResponse(responseCode = "404", description = "User not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<UserDTO> getUserById(@Parameter(description = "ID of the user to retrieve", required = true, example = "1") @PathVariable Long id) {
    try {
      UserDTO user = userService.getUserById(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
