package com.quixindo.service.impl;

import com.quixindo.domain.model.Task;
import com.quixindo.domain.model.User;
import com.quixindo.domain.repository.TaskRepository;
import com.quixindo.domain.repository.UserRepository;
import com.quixindo.dto.TaskDTO;
import com.quixindo.exceptions.TaskNotFoundException;
import com.quixindo.exceptions.UserNotFoundException;
import com.quixindo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(
                task -> new TaskDTO(
                        task.getDescription(),
                        task.isCompleted(),
                        task.getUser().getId()
                ))
                .toList();
    }

    @Override
    public TaskDTO findById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException("Tarefa nao encontrada pelo id passado."));
        return new TaskDTO(
                task.getDescription(),
                task.isCompleted(),
                task.getUser().getId()
        );
    }

    @Override
    public TaskDTO save(UUID user_id, TaskDTO taskToSave) {
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new UserNotFoundException("Usuario nao encontrado pelo id passado.")
        );
        Task task = new Task();
        task.setDescription(taskToSave.description());
        task.setCompleted(taskToSave.status());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return new TaskDTO(
                savedTask.getDescription(),
                savedTask.isCompleted(),
                savedTask.getUser().getId()
        );
    }

    @Override
    public TaskDTO update(UUID id, TaskDTO taskToUpdate) {
        Task existingTask = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task nao encontrado pelo id informado.")
        );

        existingTask.setDescription(taskToUpdate.description());
        existingTask.setCompleted(taskToUpdate.status());

        Task updatedTask = taskRepository.save(existingTask);
        return new TaskDTO(
                updatedTask.getDescription(),
                updatedTask.isCompleted(),
                updatedTask.getUser().getId()
        );
    }

    @Override
    public Task delete(UUID id) {
        return null;
    }
}
