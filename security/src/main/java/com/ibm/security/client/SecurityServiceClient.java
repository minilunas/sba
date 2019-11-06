package com.ibm.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "http://localhost:9000/user/user")
public interface SecurityServiceClient {

    @RequestMapping(value = "/queryuser", method = RequestMethod.GET)
    ResponseEntity<Object> queryUser(@RequestParam("email") String email);

}
