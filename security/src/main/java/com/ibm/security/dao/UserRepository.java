package com.ibm.security.dao;

import com.ibm.security.entity.SbaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<SbaUser, Long> {

    List<SbaUser> findByEmail(String email);

}
