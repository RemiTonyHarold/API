package com.remirobert;

import java.util.Date;

/**
 * Created by remirobert on 05/01/2017.
 */
public class TokenResponse {

    public String token;
    public String expireDate;

    public TokenResponse(Token token) {
        this.token = token.token;
        this.expireDate = token.expireDate.toString();
    }
}
