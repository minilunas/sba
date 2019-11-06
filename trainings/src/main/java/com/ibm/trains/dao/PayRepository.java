package com.ibm.trains.dao;

import com.ibm.trains.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayRepository extends JpaRepository<Payments, Long> {
    List<Payments> findByTrainId(Long trainId);

    List<Payments> findByTrainIdAndStage(Long trainId, int i);

    @Query(value = "select sum(payAmount) from Payments p where p.trainId=?1")
    double sumPayedAmount(Long id);
}
