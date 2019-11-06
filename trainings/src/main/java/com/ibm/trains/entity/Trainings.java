package com.ibm.trains.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trainings")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Trainings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "date_range")
    private String dateRange;

    @Column(name = "class_count")
    private int classCount;//总课程数，一天两节

    //apply，accept，reject,start,finish
    @Column(name = "status")
    private String status;

    @Column(name = "pay_status")
    private String payStatus;

    @Column(name = "payAmout")
    private double payAmount;

    @Column(name = "pay_date")
    private Date pay_date;


    @Column(name = "remarks")
    private String remarks;

    @Column(name = "mentor_id", nullable = false)
    private Long mentorId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "mentor_email", nullable = false)
    private String mentorEmail;

    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    @Column(name = "point")
    private double point;

    @Column(name = "point_remark")
    private double pointRemark;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "update_date")
    private Date update_date;


}
