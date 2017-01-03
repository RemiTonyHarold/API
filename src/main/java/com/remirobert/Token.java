package com.remirobert;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */
public class Token {

    @Id
    public String id;
    public String token;
    public Date expireDate;

    public Token(String token) {
        id = UUID.randomUUID().toString();
        this.token = token;
        expireDate = TokenUtils.dateExpiration();
    }
}
