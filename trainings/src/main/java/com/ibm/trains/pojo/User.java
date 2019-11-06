package com.ibm.trains.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {


    String technologyStr;
    double yearofExperience;
    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private int active;
    private String role;
    private String profile;


    private double rating;


}