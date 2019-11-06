package com.ibm.trains.dao;

import com.ibm.trains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    @Query(value = "select u from User u,MentorSkills m where m.userId=u.id and m.skill=?1", nativeQuery = true)
    List<User> findUserBySkill(String skill);

    List<User> findByFirstname(String email);

    List<User> findByCurToken(String token);
}
