package com.remirobert;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date date;
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
        description = news.getDescription();
        link = news.getLink();
        guid = news.getGuid();
        creator = news.getCreator();
        thumbnail = news.getThumbnail();

        date = news.getPubDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        pubDate = df.format(news.getPubDate());
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

    public Date getDate() {
        return date;
    }
}
