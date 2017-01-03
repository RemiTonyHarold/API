package com.remirobert;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by remirobert on 31/12/2016.
 */

@RestController
public class HelloController {

    @AuthorizationRequired
    @RequestMapping(value = "/home", method = RequestMethod.GET, produces = "application/json")
    public String hello() {
        return "hello world : " + UUID.randomUUID().toString();
    }
}
