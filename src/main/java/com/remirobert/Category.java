package com.remirobert;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by remirobert on 11/01/2017.
 */
@Document(collection = "category")
public class Category {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
