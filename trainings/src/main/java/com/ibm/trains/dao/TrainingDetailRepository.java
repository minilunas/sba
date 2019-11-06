package com.ibm.trains.dao;

import com.ibm.trains.entity.TrainingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingDetailRepository extends JpaRepository<TrainingDetail, Long> {
    List<TrainingDetail> findByTrainId(Long trainId);

    List<TrainingDetail> findByTrainIdAndStatus(Long id, String finish);

    @Query("select sum(d.point)  from TrainingDetail d where d.trainId=?1")
    double sumFinishPoint(Long trainId);

}
