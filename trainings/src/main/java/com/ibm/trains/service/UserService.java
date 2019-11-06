package com.ibm.trains.service;

import com.ibm.trains.dao.UserRepository;
import com.ibm.trains.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


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

    public User findByToken(String token) {
        List<User> users = userRepository.findByCurToken(token);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public void updateToeknByUsername(String username, String authToken) {
        List<User> userList = userRepository.findByEmail(username);
        if (userList.size() > 0) {
            userList.get(0).setCurToken(authToken);
            userRepository.save(userList.get(0));
        }
    }

    public void saveRating(double rating, String mentorEmail) {
        User user = this.findByEmail(mentorEmail);
        user.setRating(rating);
        userRepository.save(user);

    }
}
