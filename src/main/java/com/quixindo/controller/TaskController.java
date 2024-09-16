package com.quixindo.controller;

import com.quixindo.domain.model.Task;
import com.quixindo.dto.TaskDTO;
import com.quixindo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/action")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Task>> getALl(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Task> getById(@PathVariable @Valid UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<TaskDTO> save(@RequestBody @Valid TaskDTO taskDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskDTO.user_id(), taskDTO));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable @Valid UUID id, TaskDTO taskDTO){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, taskDTO));
    }

}
