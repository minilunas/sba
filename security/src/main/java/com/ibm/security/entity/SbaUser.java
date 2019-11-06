package com.ibm.security.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SbaUser {

    @Column(name = "technologyStr")
    String technologyStr;
    @Column(name = "yearofExperience")
    double yearofExperience;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private int active;
    @Column(name = "role")
    private String role;
    @Column(name = "profile")
    private String profile;

    @Column(name = "rating")
    private double rating;

    @Column(name = "cur_token")
    private String curToken;
}
