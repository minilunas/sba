package com.ibm.trains.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_datail")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class TrainingDetail {

    @Column(name = "train_id")
    Long trainId;
    @Column(name = "mentor_email")
    String mentorEmail;
    @Column(name = "student_email")
    String studentEmail;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "train_date")
    private Date train_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "status")
    private String status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "point")
    private double point;


}
