package com.ibm.security.service;

import com.ibm.security.dao.UserRepository;
import com.ibm.security.entity.SbaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public void updateToeknByUsername(String username, String authToken) {
        List<SbaUser> userList = userRepository.findByEmail(username);
        if (userList.size() > 0) {
            userList.get(0).setCurToken(authToken);
            userRepository.save(userList.get(0));
        }
    }
}
