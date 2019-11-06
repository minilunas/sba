package com.ibm.trains.service;

import com.ibm.trains.dao.PayRepository;
import com.ibm.trains.entity.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PayRepository payRepository;

    public List<Payments> queryPayInfo(Long trainId) {
        return payRepository.findByTrainId(trainId);
    }
}
