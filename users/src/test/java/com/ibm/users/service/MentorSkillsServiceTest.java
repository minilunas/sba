package com.ibm.users.service;

import com.ibm.users.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
public class MentorSkillsServiceTest {

    @Autowired
    MentorSkillsService mentorSkillsService;

    @Autowired
    UserService userService;

    @Test
    public void name() {
        User user = new User();
        user.setEmail("testtt@email.com");
        user.setTechnologyStr("javatt,gott");
        user.setRole("mentor");
        userService.addUser(user);
        assertThat(mentorSkillsService.findUserBySkill("").size(), greaterThanOrEqualTo(0));
        assertThat(mentorSkillsService.findUserBySkill("javatt").size(), greaterThanOrEqualTo(0));
    }
}