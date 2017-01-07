package com.remirobert;

import java.util.Date;

/**
 * Created by remirobert on 05/01/2017.
 */
public class TokenResponse {

    public String accessToken;
    public String expireDate;

    public TokenResponse(Token token) {
        this.accessToken = token.accessToken;
        this.expireDate = token.expireDate.toString();
    }
}
