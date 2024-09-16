package com.quixindo.service;

import com.quixindo.domain.model.Task;
import com.quixindo.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> findAll();
    TaskDTO findById(UUID id);
    TaskDTO save(UUID user_id, TaskDTO taskToSave);
    TaskDTO update(UUID id, TaskDTO taskToUpdate);
    Task delete(UUID id);
}
