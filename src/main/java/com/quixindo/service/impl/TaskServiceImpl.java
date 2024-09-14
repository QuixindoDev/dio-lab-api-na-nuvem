package com.quixindo.service.impl;

import com.quixindo.domain.model.Task;
import com.quixindo.domain.repository.TaskRepository;
import com.quixindo.domain.repository.UserRepository;
import com.quixindo.dto.TaskDTO;
import com.quixindo.service.TaskService;
import org.springframework.stereotype.Service;

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
    public Task findAll() {
        return null;
    }

    @Override
    public Task findById(UUID id) {
        return null;
    }

    @Override
    public Task save(TaskDTO taskToSave) {
        return null;
    }

    @Override
    public Task update(UUID id, TaskDTO taskToUpdate) {
        return null;
    }

    @Override
    public Task delete(UUID id) {
        return null;
    }
}
