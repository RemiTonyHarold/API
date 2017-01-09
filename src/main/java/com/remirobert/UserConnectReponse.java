package com.remirobert;

/**
 * Created by remirobert on 06/01/2017.
 */
public class UserConnectReponse {

    private User user;
    private TokenResponseAuthorization token;

    public UserConnectReponse(User user, TokenResponseAuthorization tokenResponse) {
        this.user = user;
        this.token = tokenResponse;
    }

    public User getUser() {
        return user;
    }

    public TokenResponseAuthorization getToken() {
        return token;
    }
}
