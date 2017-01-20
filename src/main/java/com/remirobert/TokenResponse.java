package com.remirobert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by remirobert on 05/01/2017.
 */
public class TokenResponse {

    private String accessToken;
    private String expireDate;

    public TokenResponse(Token token) {
        this.accessToken = token.getAccessToken();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        expireDate = df.format(token.getExpireDate());
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpireDate() {
        return expireDate;
    }
}
