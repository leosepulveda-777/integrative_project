package com.cesde.project.controller;

import com.cesde.project.dto.TaskDTO;
import com.cesde.project.model.Task;
import com.cesde.project.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Obtener todas las tareas
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return task != null ? convertToDTO(task) : null;
    }

    // Crear nueva tarea
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        return convertToDTO(taskService.createTask(task));
    }

    // Eliminar una tarea
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // --- Métodos de conversión DTO ↔ Entidad ---
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        return dto;
    }

    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        return task;
    }
}