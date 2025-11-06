package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Task;
import com.example.models.User;
import com.example.repositories.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task createTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Task existing, Task updates) {
        existing.setTitle(updates.getTitle());
        existing.setDescription(updates.getDescription());
        existing.setCompleted(updates.isCompleted());
        return taskRepository.save(existing);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
