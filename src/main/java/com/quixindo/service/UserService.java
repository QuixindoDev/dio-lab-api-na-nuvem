package com.quixindo.service;

import com.quixindo.domain.model.User;
import com.quixindo.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    User findById(UUID id);
    User save(UserDTO userToSave);
    User update(UUID id, UserDTO userToUpdate);
    void delete(UUID id);
}
