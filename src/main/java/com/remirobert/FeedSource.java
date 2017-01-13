package com.remirobert;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by remirobert on 12/01/2017.
 */
@Document(collection = "feedSource")
public class FeedSource {

    private String id;
    private String categoryId;
    private String name;
    private String url;

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
