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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public UserConnectReponse hello(@RequestParam(value = "email") String email,
                                    @RequestParam(value = "password") String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new UserCreationException();
        }
        User user = new User(email, password);
        Token token = new Token();
        token.setUserId(user.getId());
        tokenRepository.insert(token);
        userRepository.insert(user);
        return new UserConnectReponse(user, new TokenResponseAuthorization(token));
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public TokenResponseAuthorization login(HttpServletRequest request,
                                            @RequestParam(value = "email") String email,
                                            @RequestParam(value = "password") String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        Token token = tokenRepository.findByUserId(user.getId());
        token.generateNewToken();
        tokenRepository.save(token);
        return new TokenResponseAuthorization(token);
    }
}
