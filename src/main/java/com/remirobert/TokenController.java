package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by remirobert on 09/01/2017.
 */

@RestController
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public TokenResponse refreshToken(@PathVariable("token") String token) {
        Token tokenUser = tokenRepository.findByRefreshToken(token);
        if (tokenUser == null) {
            throw new TokenNotFoundException();
        }
        tokenUser.generateNewToken();
        tokenRepository.save(tokenUser);
        return new TokenResponse(tokenUser);
    }
}
