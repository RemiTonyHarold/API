package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */

@RestController
public class AuthorizationController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public User hello(HttpServletRequest request,
                      @RequestParam(value="email", required=true) String email,
                      @RequestParam(value="password", required=false) String password) {
        User user = new User(email, password);
        Token token = new Token(TokenUtils.generateNewToken());
        tokenRepository.insert(token);
        user.token = token;
        userRepository.insert(user);
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public TokenResponse login() {
        Token token = new Token(TokenUtils.generateNewToken());
        tokenRepository.save(token);
        return new TokenResponse(token);
    }
}
