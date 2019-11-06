package com.ibm.users.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "mentorskills")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MentorSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "skill")
    private String skill;


}
