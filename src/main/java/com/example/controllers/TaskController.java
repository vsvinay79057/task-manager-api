package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Task;
import com.example.models.User;
import com.example.repositories.TaskRepository;
import com.example.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    
    @Operation(summary = "Get all tasks for logged-in user",
    responses = {
    @ApiResponse(responseCode = "200", description = "Tasks retrieved",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = Task.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getAllTasksForUser(user));
    }
 
    @Operation(summary = "Get task by ID for logged-in user",
    responses = {
    @ApiResponse(responseCode = "200", description = "Task found",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = Task.class))),
    @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id, @AuthenticationPrincipal User user) {
    	Optional<Task> taskOptional = taskRepository.findByIdAndUser(id, user);
    	 if (taskOptional.isPresent()) {
    	        Task task = taskOptional.get();
    	        return ResponseEntity.ok(task);
    	    }
    	    return ResponseEntity.status(404).body("Task Not Found");
    	}
    
    @Operation(summary = "Create a new task",
    responses = {
    @ApiResponse(responseCode = "201", description = "Task created",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = Task.class)))
    })
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task, @AuthenticationPrincipal User user) {
        Task created = taskService.createTask(task, user);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Update an existing task by ID",
    responses = {
    @ApiResponse(responseCode = "200", description = "Task updated",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = Task.class))),
    @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task updates, @AuthenticationPrincipal User user) {
    	Optional<Task> taskOptional = taskRepository.findByIdAndUser(id, user);

    	if (taskOptional.isPresent())
    	{
    	    Task existingTask = taskOptional.get();
    	    Task updatedTask = taskService.updateTask(existingTask, updates);
    	    return ResponseEntity.ok(updatedTask);
    	}
    	else 
    	  {
    	    return ResponseEntity.status(404).body("Task not found");
    	  }
    }

    @Operation(summary = "Delete a task by ID",
    responses = {
    @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
    	 Optional<Task> taskOptional = taskRepository.findByIdAndUser(id, user);

    	    if (taskOptional.isPresent()) {
    	        Task task = taskOptional.get();
    	        taskService.deleteTask(task);
    	        return ResponseEntity.ok("Delete successfully");
    	    } else {
    	        return ResponseEntity.status(404).body("Task not found");
    	    }
    }
}
