package com.remirobert;

/**
 * Created by remirobert on 09/01/2017.
 */
public class TokenResponseAuthorization {

    private String accessToken;
    private String refreshToken;
    private String expireDate;

    public TokenResponseAuthorization(Token token) {
        accessToken = token.getAccessToken();
        refreshToken = token.getRefreshToken();
        expireDate = token.getExpireDate().toString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpireDate() {
        return expireDate;
    }
}
