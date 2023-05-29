package com.example.demo.utils;

import com.example.demo.entities.User;
import com.example.demo.entities.dtos.UserDTO;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class UserCreator {

    private static final Long ID = 1L;
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";
    private static final String EMAIL = "user@example.com";
    private static final String NAME = "John";
    private static final Date BIRTH_DATE = getDate("10/02/1997");

    public static User user() {
        return User.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .name(NAME)
                .birthDate(BIRTH_DATE)
                .build();
    }

    public static UserDTO userDTO() {
        return UserDTO.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .name(NAME)
                .birthDate(BIRTH_DATE)
                .build();
    }

    @SneakyThrows
    private static Date getDate(String source) {
        return new SimpleDateFormat("dd/MM/yyyy").parse(source);
    }

}
