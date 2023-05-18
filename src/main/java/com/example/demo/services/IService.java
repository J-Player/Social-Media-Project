package com.example.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface IService<T1, T2> {

    T1 findById(Long id);
    Iterable<T1> findAll();
    T1 save(T2 t2);
    void update(T2 t2, Long id);
    void delete(Long id);

    default ResponseStatusException getNotFoundException(String mensagem) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, mensagem);
    }

}
