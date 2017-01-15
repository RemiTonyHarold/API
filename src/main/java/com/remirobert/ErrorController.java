package com.remirobert;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by remirobert on 01/01/2017.
 */

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, Object> handelr() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("error", "Unauthorized access.");
        return m1;
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, Object> handelr2() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("error", "Token expired.");
        return m1;
    }

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, Object> tokenNotFound() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("error", "Token not found or expired.");
        return m1;
    }

    @ExceptionHandler(SourceIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, Object> sourceIdNotFound() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("error", "Source feed not found.");
        return m1;
    }

    @ExceptionHandler(UserCreationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, Object> userAlreadyExist() {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("error", "Email already exist.");
        return m1;
    }
}
