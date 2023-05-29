package com.example.demo.controllers.impl;

import com.example.demo.controllers.IController;
import com.example.demo.entities.User;
import com.example.demo.entities.dtos.UserDTO;
import com.example.demo.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements IController<User, UserDTO> {

    private final UserService service;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(service.save(userDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        service.update(userDTO, id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
