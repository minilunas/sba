package com.ibm.users.service;

import com.ibm.users.dao.MentorSkillsRepository;
import com.ibm.users.dao.UserRepository;
import com.ibm.users.entity.MentorSkills;
import com.ibm.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MentorSkillsRepository mentorSkillsRepository;

    public User addUser(User user) {

        user = userRepository.save(user);

        //保存技术
        if (user.getRole().equals("mentor")) {
            String[] skills = user.getTechnologyStr().split(",");
            List<MentorSkills> skillsList = new ArrayList<>();
            for (String s : skills) {
                MentorSkills tech = new MentorSkills();
                tech.setSkill(s);
                tech.setUserId(user.getId());
                skillsList.add(tech);
            }
            mentorSkillsRepository.saveAll(skillsList);

        }
        return user;
    }

    public int checkUser(User user) {
        List userList = userRepository.findByEmail(user.getEmail());
        if (userList.size() == 0) {
            userList = userRepository.findByFirstname(user.getEmail());
        }

        return userList.size();
    }

    public User findByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public User findByFirstname(String email) {
        List<User> users = userRepository.findByFirstname(email);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }


}
