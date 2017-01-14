package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by remirobert on 09/01/2017.
 */

@RestController
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value = "/accessToken", method = RequestMethod.POST)
    public TokenResponse refreshToken(@RequestParam(value = "refreshToken") String token) {
        Token tokenUser = tokenRepository.findByRefreshToken(token);
        if (tokenUser == null) {
            throw new TokenNotFoundException();
        }
        tokenUser.generateNewToken();
        tokenRepository.save(tokenUser);
        return new TokenResponse(tokenUser);
    }
}
