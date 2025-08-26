package com.example.project_ps_real;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.project_ps_real.controller.UserController;
import com.example.project_ps_real.entity.User;
import com.example.project_ps_real.repository.UserRepository;
import com.example.project_ps_real.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testUpdateUser() throws Exception {
        User user = new User(8L, "DAna", "password123", "john@example.com", "1234567890", 4.5f, false, false);

        mockMvc.perform(put("http://localhost:8080/users/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));

        User savedUser = userRepository.findById(user.getUserId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals("DAna", savedUser.getName());
        assertEquals("john@example.com", savedUser.getEmail());
    }
}