package com.quixindo.service.impl;

import com.quixindo.domain.model.User;
import com.quixindo.domain.repository.UserRepository;
import com.quixindo.dto.UserDTO;
import com.quixindo.exceptions.BadRequestException;
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
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(
                user -> new UserDTO(user.getName(), user.getEmail(), user.getPassword()))
                .toList();
    }

    @Override
    public UserDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Usuario com o id: " + id + " nao foi encontrado."));

        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public User save(UserDTO userToSave) {
        Optional<User> existingUser = userRepository.findByEmail(userToSave.email());
        if (existingUser.isPresent())
            throw new BadRequestException("O email do usuario ja existe");

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
