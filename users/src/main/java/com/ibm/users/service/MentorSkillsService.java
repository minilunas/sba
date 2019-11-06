package com.ibm.users.service;

import com.ibm.users.dao.MentorSkillsRepository;
import com.ibm.users.dao.UserRepository;
import com.ibm.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorSkillsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MentorSkillsRepository mentorSkillsRepository;


    public List<User> findUserBySkill(String skill) {

        if (skill.equals("")) {
            return userRepository.findAllMentor();
        } else {
            return userRepository.findUserBySkill(skill);
        }

    }
}
