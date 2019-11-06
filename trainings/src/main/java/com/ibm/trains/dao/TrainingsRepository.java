package com.ibm.trains.dao;

import com.ibm.trains.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingsRepository extends JpaRepository<Trainings, Long> {
    List<Trainings> findByStudentEmail(String email);

    List<Trainings> findByMentorEmail(String email);

    List<Trainings> findByMentorId(Long mentorId);

    @Query("select distinct t from Trainings t,TrainingDetail d where t.id=d.trainId and d.status='finish'")
    List<Trainings> findHasFinishClassTrainList();

    @Query("select sum(t.point)  from Trainings t where t.mentorEmail=?1")
    double sumPoint(String mentorEmail);
}
