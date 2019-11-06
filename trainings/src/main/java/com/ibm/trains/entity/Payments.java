package com.ibm.trains.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Payments {

    @Column(name = "train_id")
    Long trainId;
    @Column(name = "stage")
    int stage;//阶段
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "payAmout")
    private double payAmount;//付款金额

    @Column(name = "create_date")
    private Date create_date;//付款日期


}
