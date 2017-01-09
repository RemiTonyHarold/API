package com.remirobert;

import java.util.Date;

/**
 * Created by remirobert on 05/01/2017.
 */
public class TokenResponse {

    private String accessToken;
    private String expireDate;

    public TokenResponse(Token token) {
        this.accessToken = token.getAccessToken();
        this.expireDate = token.getExpireDate().toString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpireDate() {
        return expireDate;
    }
}
