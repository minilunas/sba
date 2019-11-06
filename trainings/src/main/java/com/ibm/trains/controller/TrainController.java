package com.ibm.trains.controller;

import com.ibm.trains.entity.TrainingDetail;
import com.ibm.trains.service.PaymentService;
import com.ibm.trains.service.TrainingsService;
import com.ibm.users.base.RspModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/training")
@RestController
@Api(description = "SBA Training Interface")
public class TrainController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    private TrainingsService trainingsService;

    /**
     * 上课完成
     *
     * @param trainingDetail
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/completeClass", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "complete train")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 202, message = "Account Exist"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> completeClass(@ApiParam(name = "body", required = true) @RequestBody TrainingDetail trainingDetail) {

        try {
            trainingsService.completeClassByDetailId(trainingDetail.getId());
            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("User Created");
            return new ResponseEntity<RspModel>(rsp, HttpStatus.CREATED);


        } catch (Exception ex) {
            ex.printStackTrace();
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());

            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * 用户打分
     *
     * @param trainingDetail
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/commentClass", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "comment Class by student")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> commentClass(@ApiParam(name = "body", required = true) @RequestBody TrainingDetail trainingDetail) {

        try {
            this.trainingsService.commentClass(trainingDetail);
            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData(null);
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //查询付款情况
    @CrossOrigin
    @RequestMapping(value = "/queryPayInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "query Pay Info")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> queryPayInfo(@ApiParam(name = "trainId", required = true) @RequestParam Long trainId) {

        try {

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData(paymentService.queryPayInfo(trainId));
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //查询培训情况
    @CrossOrigin
    @RequestMapping(value = "/queryTrainDetail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "query Trai nDetail")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 202, message = "Account Exist"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> queryTrainDetail(
            @ApiParam(name = "trainId", required = true) @RequestParam Long trainId) {

        try {

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("Success");
            rsp.setData(this.trainingsService.findDetailListByTrainId(trainId));
            return new ResponseEntity<RspModel>(rsp, HttpStatus.CREATED);


        } catch (Exception ex) {
            ex.printStackTrace();
            RspModel rsp = new RspModel();

            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());

            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
