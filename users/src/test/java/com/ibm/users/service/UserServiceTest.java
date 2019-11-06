package com.ibm.users.service;

import com.ibm.users.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    void checkUser() {
        User user = new User();
        user.setFirstname("222");
        user.setEmail("444");
        assertEquals(userService.checkUser(user), 0);
        assertNull(userService.findByEmail("998"));
        assertNull(userService.findByFirstname("998"));
    }

    @Test
    void findByEmail() {
        assertNull(userService.findByEmail(UUID.randomUUID().toString()));
    }
}