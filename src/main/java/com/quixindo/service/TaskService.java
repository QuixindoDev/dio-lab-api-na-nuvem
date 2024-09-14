package com.quixindo.service;

import com.quixindo.domain.model.Task;
import com.quixindo.dto.TaskDTO;

import java.util.UUID;

public interface TaskService {
    Task findAll();
    Task findById(UUID id);
    Task save(TaskDTO taskToSave);
    Task update(UUID id, TaskDTO taskToUpdate);
    Task delete(UUID id);
}
