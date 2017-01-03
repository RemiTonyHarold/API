package com.remirobert;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */
public class User {

    @Id
    public String id;
    public String mail;
    public String password;

    public User(String mail, String password) {
        id = UUID.randomUUID().toString();
        this.mail = mail;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, mail='%s', password='%s']",
                id, mail, password);
    }
}
