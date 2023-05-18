package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.entities.dtos.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.IService;
import com.example.demo.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, UserDTO> {

    private final UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> getNotFoundException("User not found"));
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = Mapper.INSTANCE.map(userDTO, User.class);
        return repository.save(user);
    }

    @Override
    public void update(UserDTO userDTO, Long id) {
        User user = findById(id);
        Mapper.INSTANCE.map(userDTO, user);
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

}