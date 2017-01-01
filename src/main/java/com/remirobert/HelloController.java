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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String hello() {
        return "hello world : " + UUID.randomUUID().toString();
    }
}
