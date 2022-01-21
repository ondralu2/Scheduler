package com.project.scheduler;

import com.project.scheduler.entity.User;
import com.project.scheduler.services.UserService;
import com.project.scheduler.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void testCreateUsers()
            throws Exception {

        User user1 = new User();
        user1.setUsername("user1");
        user1.setFirstName("User");
        user1.setLastName("Surname");
        user1.setPassword("password");
        user1.setEmail("user1@test.com");
        user1.setPhone("+420987654321");

        List<User> users = Arrays.asList(user1);

        given(service.findAll()).willReturn(users);

        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is(user1.getUsername())))
                .andExpect(jsonPath("$[0].firstName", is(user1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(user1.getLastName())))
                .andExpect(jsonPath("$[0].password", is(user1.getPassword())))
                .andExpect(jsonPath("$[0].email", is(user1.getEmail())))
                .andExpect(jsonPath("$[0].phone", is(user1.getPhone())));

        User user2 = new User();
        user2.setUsername("user2");
        user2.setFirstName("Test");
        user2.setLastName("Tester");
        user2.setPassword("passwd");
        user2.setEmail("test@tester.co.uk");
        user2.setPhone("+44 7911 123456");

        users = Arrays.asList(user1, user2);

        given(service.findAll()).willReturn(users);

        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].username", is(user2.getUsername())))
                .andExpect(jsonPath("$[1].firstName", is(user2.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(user2.getLastName())))
                .andExpect(jsonPath("$[1].password", is(user2.getPassword())))
                .andExpect(jsonPath("$[1].email", is(user2.getEmail())))
                .andExpect(jsonPath("$[1].phone", is(user2.getPhone())));
    }
}
