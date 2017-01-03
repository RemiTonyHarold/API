package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
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
    private TokenRepository repository;

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
        calendar.add(Calendar.SECOND, 5);
        return calendar.getTime();
    }

    public Boolean isTokenValid(String token) {
        Token tokenFound = repository.findByToken(token);
        if (tokenFound == null) {
            throw new AuthorizationException();
        }
        return true;
    }
}
