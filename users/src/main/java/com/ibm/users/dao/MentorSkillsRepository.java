package com.ibm.users.dao;

import com.ibm.users.entity.MentorSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorSkillsRepository extends JpaRepository<MentorSkills, Long> {
    List<MentorSkills> findBySkill(String skill);
}
