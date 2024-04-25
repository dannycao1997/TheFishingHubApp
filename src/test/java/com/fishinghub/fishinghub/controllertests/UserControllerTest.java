package com.fishinghub.fishinghub.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishinghub.fishinghub.controller.UserController;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");
    }

    @Test
    @WithMockUser
    public void registerUserTest() throws Exception {
        given(userService.registerUser(any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @WithMockUser
    public void loginUserTest() throws Exception {
        given(userService.validateUser(eq(user.getUsername()), eq("password123"))).willReturn(user);

        mockMvc.perform(post("/api/users/login")
                        .param("username", user.getUsername())
                        .param("password", "password123")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @WithMockUser
    public void getAllUsersTest() throws Exception {
        List<User> users = Arrays.asList(user);
        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(get("/api/users")
                 .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(user.getUsername()));
    }

    @Test
    @WithMockUser
    public void getUserByIdTest() throws Exception {
        given(userService.getUserById(user.getId())).willReturn(user);

        mockMvc.perform(get("/api/users/{id}", user.getId())
                 .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @WithMockUser
    public void updateUserTest() throws Exception {
        given(userService.updateUser(any(User.class))).willReturn(user);

        mockMvc.perform(put("/api/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @WithMockUser
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", user.getId())
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(user.getId());
    }
}
