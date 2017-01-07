package com.remirobert;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static javax.accessibility.AccessibleRole.SEPARATOR;

/**
 * Created by remirobert on 02/01/2017.
 */

@Service
public class TokenUtils {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public static String generateNewToken() {
        Random random = new SecureRandom();
        char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] result = new char[256];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

    public static Date dateExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        return calendar.getTime();
    }

    public Boolean checkExpirationToken(Token token) {
        return (new Date().before(token.expireDate));
    }

    public User isTokenValid(String token) {
        Token tokenFound = tokenRepository.findByAccessToken(token);
        if (tokenFound == null) {
            throw new AuthorizationException();
        }
        User user = userRepository.findById(tokenFound.userId);
        if (user == null) {
            throw new AuthorizationException();
        }
        if (!checkExpirationToken(tokenFound)) {
            throw new TokenExpiredException();
        }
        return user;
    }
}
