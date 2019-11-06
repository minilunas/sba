package com.ibm.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "success";
    }
}

