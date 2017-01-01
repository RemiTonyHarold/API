package com.remirobert;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by remirobert on 01/01/2017.
 */

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Map<String, Object> handelr() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("code", 401);
        m1.put("status", "error");
        m1.put("message", "Sorry, your provided token information expired or not exists.");
        return m1;
    }
}
