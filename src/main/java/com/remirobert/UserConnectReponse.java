package com.remirobert;

/**
 * Created by remirobert on 06/01/2017.
 */
public class UserConnectReponse {

    public User user;
    public TokenResponse token;

    public UserConnectReponse(User user, TokenResponse tokenResponse) {
        this.user = user;
        this.token = tokenResponse;
    }
}
