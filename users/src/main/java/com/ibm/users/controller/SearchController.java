package com.ibm.users.controller;

import com.ibm.users.base.RspModel;
import com.ibm.users.entity.User;
import com.ibm.users.service.MentorSkillsService;
import com.ibm.users.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "SBA Search Interface")
public class SearchController {
    @Autowired
    private UserService userService;

    @Autowired
    private MentorSkillsService mentorSkillsService;

    @CrossOrigin
    @RequestMapping(value = "/queryMentor", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "SBA Find Mentor")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> getMentor(@ApiParam(name = "skill", required = true) @RequestParam String skill) {

        try {
            List<User> userList = mentorSkillsService.findUserBySkill(skill);

            RspModel rsp = new RspModel();
            rsp.setCode(200);
            rsp.setMessage("ok");
            rsp.setData(userList);
            return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);


        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
