package com.example.demo.controllers.impl;

import com.example.demo.controllers.IControllerTest;
import com.example.demo.controllers.annotation.ControllerTest;
import com.example.demo.entities.User;
import com.example.demo.entities.dtos.UserDTO;
import com.example.demo.services.impl.UserService;
import com.example.demo.utils.UserCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest({UserController.class})
@DisplayName("User Controller Test")
public class UserControllerTest implements IControllerTest {

    private static final String URI = "/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;
    private static final User user = UserCreator.user();
    private static final UserDTO userDTO = UserCreator.userDTO();

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    @Test
    @Override
    @SneakyThrows
    @DisplayName("findById | ")
    public void findById() {
        given(service.findById(anyLong())).willReturn(user);
        mockMvc.perform(get(URI + "/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.birthDate", is(formatDate(user.getBirthDate()))));
    }

    @Test
    @Override
    @SneakyThrows
    @DisplayName("findAll | ")
    public void findAll() {
        given(service.findAll()).willReturn(List.of(user));
        MvcResult mvcResult = mockMvc.perform(get(URI + "/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<User> users = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertFalse(users.isEmpty());
    }

    @Test
    @Override
    @SneakyThrows
    @DisplayName("save | ")
    public void save() {
        given(service.save(any(UserDTO.class))).willReturn(user);
        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.birthDate", is(formatDate(user.getBirthDate()))));
    }

    @Test
    @Override
    @SneakyThrows
    @DisplayName("update | ")
    public void update() {
        willDoNothing().given(service).update(any(UserDTO.class), anyLong());
        mockMvc.perform(put(URI + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Override
    @SneakyThrows
    @DisplayName("delete | ")
    public void delete() {
        willDoNothing().given(service).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
