package com.ibm.trains.service;

import com.ibm.trains.dao.PayRepository;
import com.ibm.trains.dao.TrainingDetailRepository;
import com.ibm.trains.dao.TrainingsRepository;
import com.ibm.trains.entity.Payments;
import com.ibm.trains.entity.TrainingDetail;
import com.ibm.trains.entity.Trainings;
import com.ibm.trains.entity.User;
import com.ibm.trains.utils.DateUtlis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TrainingsService {

    @Autowired
    TrainingsRepository trainingsRepository;

    @Autowired
    TrainingDetailRepository detailRepository;

    @Autowired
    PayRepository payRepository;
    @Autowired
    UserService userService;

    public void addProposals(Trainings trainings) {
        User user = userService.findByEmail(trainings.getStudentEmail());
        trainings.setStudentId(user.getId());
        trainings.setCreate_date(new Date());
        trainings.setStatus("apply");
        trainings.setCreate_date(new Date());
        trainings.setClassCount(DateUtlis.getTimeDiff(trainings.getStart_date(), trainings.getEnd_date()).intValue());
        trainingsRepository.save(trainings);
    }


    public List<Trainings> findProp4Student(String email, String status) {
        return trainingsRepository.findByStudentEmail(email);
    }

    public List<Trainings> findProp4Mentor(String email, String status) {
        return trainingsRepository.findByMentorEmail(email);
    }

    public void acceptOrRejectTrain(Long trainingId, String status) {
        Trainings trainings = trainingsRepository.findById(trainingId).get();
        trainings.setStatus(status);
        trainings.setUpdate_date(new Date());

        if (status.equals("accept")) {
            trainings.setClassCount(DateUtlis.getTimeDiff(trainings.getStart_date(), trainings.getEnd_date()).intValue());
            trainings.setPayAmount(100 * 2 * trainings.getClassCount());
            trainings.setPayStatus("Not Pay");
        }
        trainingsRepository.save(trainings);
    }

    public void payProposals(Long trainingId) {
        //用户付款
        Trainings train = trainingsRepository.findById(trainingId).get();
        train.setPayStatus("Payed");
        train.setPay_date(new Date());
        train.setStatus("start");

        //生成课程记录
        Date startDate = train.getStart_date();
        Date endDate = train.getEnd_date();
        List<TrainingDetail> detailList = new ArrayList<>();

        for (; startDate.compareTo(endDate) <=0 ; ) {
            TrainingDetail detail = new TrainingDetail();
            detail.setTrainId(trainingId);
            detail.setMentorEmail(train.getMentorEmail());
            detail.setStudentEmail(train.getStudentEmail());
            detail.setTrain_date(startDate);
            detail.setStatus("unstart");
            detail.setPoint(0);
            detailList.add(detail);
            startDate = DateUtlis.addDays(startDate, 1);
        }

        detailRepository.saveAll(detailList);

        trainingsRepository.save(train);
        //生成付款记录
    }


    public void completeClassByDetailId(Long detailId) {
        TrainingDetail detail = detailRepository.findById(detailId).get();
        detail.setStatus("finish");
        detailRepository.save(detail);
    }

    public void commentClass(TrainingDetail trainingDetail) {

        TrainingDetail detail = detailRepository.findById(trainingDetail.getId()).get();
        detail.setPoint(trainingDetail.getPoint());
        detailRepository.save(detail);

        Long trainId = detail.getTrainId();
        //计算培训分数
       List<TrainingDetail> detailList = detailRepository.findByTrainIdAndStatus(trainId,"finish");
       if(detailList.size()>0) {
           double sumDetailPoint = detailRepository.sumFinishPoint(trainId);
           Trainings train = trainingsRepository.findById(trainId).get();
           double p =sumDetailPoint / detailList.size();
           p=    (double) Math.round(p * 10) / 10;
           train.setPoint(p);
           trainingsRepository.save(train);
       }

        //计算导师分数
        List<Trainings> trainingList = trainingsRepository.findByMentorEmail(detail.getMentorEmail());
        if(trainingList.size()>0){
            double sumTrainPoint = trainingsRepository.sumPoint(detail.getMentorEmail());
            double p =sumTrainPoint/trainingList.size();
            p=    (double) Math.round(p * 10) / 10;
            userService.saveRating(sumTrainPoint/trainingList.size(),detail.getMentorEmail());
        }

    }

    public List<TrainingDetail> findDetailListByTrainId(Long trainId) {
        return this.detailRepository.findByTrainId(trainId);
    }

    public void execuPay() {

        //查找有完结课程的培训
        List<Trainings> hasFinishClassTrainList = trainingsRepository.findHasFinishClassTrainList();
        for (Trainings train : hasFinishClassTrainList) {
            Long trainId = train.getId();
            int sumDetail = detailRepository.findByTrainId(trainId).size();
            int sumFinish = detailRepository.findByTrainIdAndStatus(trainId, "finish").size();
            if (sumDetail > 0) {
                double curPer = Double.valueOf(sumFinish) / Double.valueOf(sumDetail);
                createPayRecord(curPer, train, 0.25, 1);
                createPayRecord(curPer, train, 0.5, 2);
                createPayRecord(curPer, train, 0.75, 3);
                createPayRecord(curPer, train, 1, 4);
            }
        }
    }

    private void createPayRecord(double curPer, Trainings train, double per, int stage) {
        if (curPer >= per) {
            List<Payments> perList = payRepository.findByTrainIdAndStage(train.getId(), stage);
            if (perList.size() == 0) {
                Payments pay = new Payments();
                pay.setStage(stage);
                pay.setCreate_date(new Date());


                if(stage==1){
                    pay.setPayAmount(train.getPayAmount() * per );
                }else {
                    double havePayed =  payRepository.sumPayedAmount(train.getId());
                    if (stage == 4) {
                        pay.setPayAmount(train.getPayAmount() - havePayed);
                    } else {
                        pay.setPayAmount(train.getPayAmount() * per - havePayed);
                    }
                }
                pay.setTrainId(train.getId());
                payRepository.save(pay);
            }
        }
    }
}
