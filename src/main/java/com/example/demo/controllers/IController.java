package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;

public interface IController<T1, T2> {

    ResponseEntity<T1> findById(Long id);
    ResponseEntity<Iterable<T1>> findAll();
    ResponseEntity<T1> save(T2 t2);
    ResponseEntity<Void> update(T2 t2, Long id);
    ResponseEntity<Void> delete(Long id);

}
