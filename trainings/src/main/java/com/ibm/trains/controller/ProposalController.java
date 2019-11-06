package com.ibm.trains.controller;

import com.ibm.trains.entity.Trainings;
import com.ibm.trains.service.TrainingsService;
import com.ibm.trains.service.UserService;
import com.ibm.users.base.RspModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/proposal")
@RestController
@Api(description = "SBA Proposal Interface")
public class ProposalController {

    @Autowired
    UserService userService;
    @Autowired
    private TrainingsService trainingsService;

    @CrossOrigin
    @RequestMapping(value = "/addProposals", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "add Proposals")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 202, message = "Account Exist"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> addProposals(HttpServletRequest request, @ApiParam(name = "body", required = true) @RequestBody Trainings trainings) {


        try {

            trainingsService.addProposals(trainings);
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


    @CrossOrigin
    @RequestMapping(value = "/queryStudentProp", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "search Proposals for student")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> queryStudentProp(@ApiParam(name = "email", required = true) @RequestParam String email, @ApiParam(name = "status", required = true) @RequestParam String status) {

        try {

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData(trainingsService.findProp4Student(email, status));
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/queryMentorProp", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "search Proposals for mentor")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> queryMentorProp(@ApiParam(name = "email", required = true) @RequestParam String email, @ApiParam(name = "status", required = true) @RequestParam String status) {

        try {

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData(trainingsService.findProp4Mentor(email, status));
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/acceptOrReject", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "accept Or Reject")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 202, message = "Account Exist"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> acceptOrReject(@ApiParam(name = "body", required = true) @RequestBody Trainings trainings) {

        try {
            if (trainings.getStatus().equals("accept")) {
                trainingsService.acceptOrRejectTrain(trainings.getId(), "accept");
            } else {
                trainingsService.acceptOrRejectTrain(trainings.getId(), "reject");
            }

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("Success");
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
     * 用户付款
     *
     * @param trainingId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/payProposals", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "user pay Proposals ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> payProposals(@ApiParam(name = "trainingId", required = true) @RequestParam Long trainingId) {

        try {
            trainingsService.payProposals(trainingId);
            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData("");
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
