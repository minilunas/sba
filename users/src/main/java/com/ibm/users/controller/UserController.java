package com.ibm.users.controller;

import com.ibm.users.base.RspModel;
import com.ibm.users.entity.User;
import com.ibm.users.service.UserService;
import com.ibm.users.utils.EncrytedPasswordUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "SBA User Interface")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/adduser", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "User Register")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 202, message = "Account Exist"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> addUser(@ApiParam(name = "body", required = true) @RequestBody User user) {

        try {
            int userCnt = userService.checkUser(user);
            if (userCnt > 0) {
                RspModel rsp = new RspModel();
                rsp.setCode(202);
                rsp.setMessage("User Exist");
                return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
            } else {
                String encrytedpassword = EncrytedPasswordUtils.encrytePassword(user.getPassword());
                user.setPassword(encrytedpassword);

                userService.addUser(user);
                RspModel rsp = new RspModel();
                rsp.setCode(200);
                rsp.setMessage("User Created");
                return new ResponseEntity<RspModel>(rsp, HttpStatus.CREATED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());

            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/queryuser", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "SBA Find User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<RspModel> queryUser(@ApiParam(name = "email", required = true) @RequestParam String email) {

        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                user = userService.findByFirstname(email);
            }
            if (user != null) {
                RspModel rsp = new RspModel();
                rsp.setCode(200);
                rsp.setMessage("ok");
                rsp.setData(user);
                return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

            } else {
                RspModel rsp = new RspModel();
                rsp.setCode(404);
                rsp.setMessage("User No Found");
                return new ResponseEntity<RspModel>(rsp, HttpStatus.NOT_FOUND);
            }


        } catch (Exception ex) {
            RspModel rsp = new RspModel();
            rsp.setCode(500);
            rsp.setMessage(ex.getMessage());
            return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
