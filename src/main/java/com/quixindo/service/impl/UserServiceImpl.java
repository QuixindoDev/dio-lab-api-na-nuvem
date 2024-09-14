package com.quixindo.service.impl;

import com.quixindo.domain.model.User;
import com.quixindo.domain.repository.UserRepository;
import com.quixindo.dto.UserDTO;
import com.quixindo.exceptions.UserNotFoundException;
import com.quixindo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public User save(UserDTO userToSave) {
        User user = new User();
        BeanUtils.copyProperties(userToSave, user);
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, UserDTO userToUpdate) {
        Optional<User> userToFind = userRepository.findById(id);

        if (userToFind.isEmpty())
            throw new UserNotFoundException("User Not Found");

        User userModel = userToFind.get();
        BeanUtils.copyProperties(userToUpdate, userModel, "id");

        return userRepository.save(userModel);
    }

    @Override
    public void delete(UUID id) {
        Optional<User> userToFind = userRepository.findById(id);

        if (userToFind.isEmpty())
            throw new UserNotFoundException("User Not Found");

        userRepository.deleteById(id);
    }
}
