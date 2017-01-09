package com.remirobert;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by remirobert on 31/12/2016.
 */

@RestController
public class HelloController {

    @AuthorizationRequired
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        return "hello : " + user.getEmail();
    }
}
