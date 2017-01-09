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
    private String id;
    private String accessToken;
    private String refreshToken;
    private Date expireDate;
    private String userId;

    public Token() {
        id = UUID.randomUUID().toString();
        generateNewToken();
        refreshToken= TokenUtils.generateNewToken();
    }

    public void generateNewToken() {
        accessToken = TokenUtils.generateNewToken();
        expireDate = TokenUtils.dateExpiration();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getUserId() {
        return userId;
    }
}
