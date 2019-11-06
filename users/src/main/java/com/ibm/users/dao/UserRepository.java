package com.ibm.users.dao;

import com.ibm.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    @Query(value = "select distinct u from User u,MentorSkills m where m.userId=u.id and m.skill=?1")
    List<User> findUserBySkill(String skill);

    @Query(value = "select u from User u where u.role='mentor'")
    List<User> findAllMentor();

    List<User> findByFirstname(String email);

    List<User> findByCurToken(String token);
}
