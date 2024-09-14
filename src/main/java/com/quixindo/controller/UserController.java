package com.quixindo.controller;

import com.quixindo.domain.model.User;
import com.quixindo.dto.UserDTO;
import com.quixindo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/action")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Valid UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userToSave){
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userToSave));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @RequestBody @Valid UUID id, UserDTO userToUpdate){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userToUpdate));
    }

/*    @DeleteMapping("/users/{id}")
    public User deleteUser(){
        return
    }*/

}
