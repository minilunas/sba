package com.ibm.trains.service;

import com.ibm.trains.dao.PayRepository;
import com.ibm.trains.dao.TrainingDetailRepository;
import com.ibm.trains.dao.TrainingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
public class TrainingsServiceTest {


    @Autowired
    PayRepository payRepository;


    @Test
   public void testPayAmound(){
        System.out.println(payRepository.sumPayedAmount(29l));
    }

}