package com.remirobert;

import java.util.Date;
import java.util.List;

/**
 * Created by remirobert on 14/01/2017.
 */
public class NewsCategoryResponse {


    private String id;
    private String sourceId;
    private String categoryId;
    private String title;
    private String pubDate;
    private String description;
    private String link;
    private String guid;
    private String creator;
    private String thumbnail;


    public NewsCategoryResponse(Category category, News news) {
        id = news.getId();
        categoryId = category.getId();
        sourceId = news.getSourceId();
        title = news.getTitle();
        pubDate = news.getPubDate();
        description = news.getDescription();
        link = news.getLink();
        guid = news.getGuid();
        creator = news.getCreator();
        thumbnail = news.getThumbnail();
    }

    public String getId() {
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getCreator() {
        return creator;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
