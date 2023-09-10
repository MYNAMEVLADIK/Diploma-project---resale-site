package ru.skypro.homework.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class UserControllerTest
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * Testing method getById cats in the controller class
     */
    @Test
    void getUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setEmail("zitarvita@gmail.ru");
        user.setFirstName("Витя");
        user.setLastName("Житарь");
        user.setPhone("+79507886475");
        user.setImage(null);
        User user2 = new User();
        user2.setUsername("zitarvita@gmail.ru");

        // when(userService.getUser(anyString())).thenReturn(user.getUsername());

        mockMvc.perform(
                        get("/users/me", user))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        verify(userService).getUser(user.getUsername());
    }
}
