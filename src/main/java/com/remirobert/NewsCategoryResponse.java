package com.remirobert;

import java.util.List;

/**
 * Created by remirobert on 14/01/2017.
 */
public class NewsCategoryResponse {
    private Category category;
    private List<News> news;

    public NewsCategoryResponse(Category category, List<News> news) {
        this.category = category;
        this.news = news;
    }

    public Category getCategory() {
        return category;
    }

    public List<News> getNews() {
        return news;
    }
}
