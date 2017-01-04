package com.remirobert;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */
public class User {

    @Id
    public String id;
    public String mail;
    public String password;

    @DBRef
    public Token token;

    public User(String mail, String password) {
        id = UUID.randomUUID().toString();
        this.mail = mail;
        this.password = password;
    }
}
