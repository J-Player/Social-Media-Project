package com.example.demo.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "O campo 'username' é obrigatório.")
    private String username;

    @NotEmpty(message = "O campo 'password' é obrigatório.")
    private String password;

    @NotEmpty(message = "O campo 'email' é obrigatório.")
    private String email;

    @NotEmpty(message = "O campo 'name' é obrigatório.")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "O campo 'birthDate' é obrigatório.")
    private Date birthDate;

}
