package com.remirobert;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Created by remirobert on 02/01/2017.
 */

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;

    public User(String email, String password) {
        id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
