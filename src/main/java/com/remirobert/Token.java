package com.remirobert;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */

@Document(collection = "token")
public class Token {

    @Id
    public String id;
    public String accessToken;
    public Date expireDate;
    public String userId;

    public Token() {
        id = UUID.randomUUID().toString();
        generateNewToken();
    }

    public void generateNewToken() {
        accessToken = TokenUtils.generateNewToken();
        expireDate = TokenUtils.dateExpiration();
    }
}
