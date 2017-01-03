package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */

@RestController
public class AuthorizationController {

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String hello() {
        Token token = new Token(TokenUtils.generateNewToken());
        tokenRepository.save(token);
        return "token: " + token.token;
    }


}
